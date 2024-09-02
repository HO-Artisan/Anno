package ho.artisan.anno.resolver;

import ho.artisan.anno.annotation.ID;
import ho.artisan.anno.annotation.datagen.Lang;
import ho.artisan.anno.annotation.vanilla.Reg;
import ho.artisan.anno.core.DataGenResolver;
import ho.artisan.anno.core.Entry;
import ho.artisan.anno.core.Registration;
import ho.artisan.anno.datagen.provider.AnnoLanguages;
import net.fabricmc.fabric.api.datagen.v1.FabricDataGenerator;
import net.minecraft.util.Identifier;

import java.util.List;

@ID("language")
public class LangResolver implements DataGenResolver<Lang> {
    private final AnnoLanguages.Dictionary dictionary = new AnnoLanguages.Dictionary();

    @Override
    public void process(Entry entry, Registration registration) {
        List<Lang> langs = entry.getRepeated(Lang.class);
        Identifier id = genID(entry, registration);

        for (Lang lang : langs) {
            String key = lang.key().replace("$0", id.getNamespace()).replace("$1", id.getPath());
            if (entry.has(Reg.class)) {
                String part = entry.get(Reg.class).value() + "." + id.getNamespace() + "." + id.getPath();
                key = key.replace("$", part);
            }else {
                if (key.equals("$"))
                    continue;
                key = key.replace("$", id.getNamespace() + "." + id.getPath());
            }
            dictionary.add(lang.langCode(), key, lang.value());
        }
    }

    @Override
    public Class<Lang> aClass() {
        return Lang.class;
    }

    @Override
    public void finish(FabricDataGenerator generator, Registration registration) {
        for (String code : dictionary.langCodes()) {
            generator.addProvider(new AnnoLanguages(generator, code, dictionary, registration.id()));
        }
    }
}
