package ho.artisan.anno.core.resolver.datagen;

import ho.artisan.anno.core.LangMap;
import ho.artisan.anno.core.annotation.datagen.Lang;
import ho.artisan.anno.core.annotation.vanilla.Reg;
import ho.artisan.anno.core.resolver.DataGenResolver;
import ho.artisan.anno.datagen.provider.AnnoLanguageProvider;
import net.fabricmc.fabric.api.datagen.v1.FabricDataGenerator;
import net.minecraft.data.DataProvider;
import net.minecraft.util.Identifier;
import net.minecraft.util.Util;

import java.lang.reflect.Field;

public class LangResolver implements DataGenResolver<Lang> {
    private final LangMap map = new LangMap();

    @Override
    public <T> void process(Target<T> target, Class<?> registration) {
        Lang[] langs = target.field().getAnnotationsByType(Lang.class);
        Identifier id = getID(target, registration);

        for (Lang lang : langs) {
            String key = lang.key().replace("$0", id.getNamespace()).replace("$1", id.getPath());
            if (target.field().isAnnotationPresent(Reg.class)) {
                String $part = Util.createTranslationKey(target.field().getAnnotation(Reg.class).value(), id);
                key = key.replace("$", $part);
            }else {
                if (key.equals("$"))
                    continue;
                key = key.replace("$", id.getNamespace() + "." + id.getPath());
            }
            map.add(lang.langCode(), key, lang.value());
        }
    }

    @Override
    public void apply(FabricDataGenerator generator, FabricDataGenerator.Pack pack, Class<?> registration) {
        for (String langCode : map.langCodes()) {
            pack.addProvider((FabricDataGenerator.Pack.RegistryDependentFactory<DataProvider>) (output, lookup) -> new AnnoLanguageProvider(output, lookup, langCode, name(), map.get(langCode)));
        }
    }

    @Override
    public Class<Lang> annoClass() {
        return Lang.class;
    }

    @Override
    public String name() {
        return "Language";
    }

    @Override
    public boolean isAnnotated(Field field) {
        return field.getAnnotationsByType(Lang.class).length > 0;
    }
}
