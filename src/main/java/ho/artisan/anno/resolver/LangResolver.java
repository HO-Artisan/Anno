package ho.artisan.anno.resolver;

import ho.artisan.anno.annotation.RegistryType;
import ho.artisan.anno.annotation.lang.Lang;
import ho.artisan.anno.annotation.lang.LangContainer;
import ho.artisan.anno.core.Entry;
import ho.artisan.anno.core.Registration;
import ho.artisan.anno.core.resolver.DataGenerationResolver;
import ho.artisan.anno.datagen.provider.AnnoLangProvider;
import ho.artisan.anno.util.LangMap;
import net.fabricmc.fabric.api.datagen.v1.FabricDataGenerator;

public final class LangResolver implements DataGenerationResolver {
    private final LangMap langMap = new LangMap();

    @Override
    public void load(FabricDataGenerator generator, Registration registration) {
        for (String code : langMap.codes()) {
            generator.addProvider(new AnnoLangProvider(generator, code, registration.id(), langMap.get(code)));
        }
    }

    @Override
    public boolean match(Entry entry) {
        return entry.contain(LangContainer.class) && entry.contain(RegistryType.class);
    }

    @Override
    public void process(Entry entry, Registration registration) {
        Lang[] langs = entry.get(LangContainer.class).value();
        RegistryType type = entry.get(RegistryType.class);
        for (Lang lang : langs) {
            String key = type.value() + '.' + registration.id() + '.' + entry.id();
            langMap.add(lang.code(), key, lang.text());
        }
    }

    @Override
    public String id() {
        return "lang";
    }
}
