package com.orchestrator.orchestrator.model.dto.userunlockable.request;

import com.orchestrator.orchestrator.model.dto.DtoTest;
import com.orchestrator.orchestrator.model.dto.EqualsTester;
import org.junit.jupiter.api.Test;

public class UserUnlockableChangeRequestDtoTest extends DtoTest<UserUnlockableChangeRequestDto> {
    @Override
    protected UserUnlockableChangeRequestDto getInstance() {
        return new UserUnlockableChangeRequestDto();
    }

    @Test
    public void testEqualsAndHashCode() {
        UserUnlockableChangeRequestDto userUnlockableChangeRequestDtoTest = new UserUnlockableChangeRequestDto();
        userUnlockableChangeRequestDtoTest.setIdUnlockable(1L);

        UserUnlockableChangeRequestDto userChangeRequestDto1 = new UserUnlockableChangeRequestDto();
        userChangeRequestDto1.setIdUnlockable(2L);
        EqualsTester<UserUnlockableChangeRequestDto> equalsTester = EqualsTester.newInstance( new UserUnlockableChangeRequestDto() );
        equalsTester.assertEqual( new UserUnlockableChangeRequestDto(), new UserUnlockableChangeRequestDto() );
        equalsTester.assertNotEqual( userUnlockableChangeRequestDtoTest, userChangeRequestDto1);
    }
}
