package net.engineeringdigest.journalApp.service;

import net.engineeringdigest.journalApp.entity.User;
import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.extension.ExtensionContext;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.ArgumentsProvider;

import java.util.stream.Stream;

@Disabled
public class UserArgumentsProvider implements ArgumentsProvider {
    @Override
    public Stream<? extends Arguments> provideArguments(ExtensionContext extensionContext) throws Exception {
        return Stream.of(
                Arguments.of(User.builder().username("absdk").password("afbnl").build())
//                Arguments.of(User.builder().username("absdkda").build())      //Password cannot be empty
        );
    }
}
