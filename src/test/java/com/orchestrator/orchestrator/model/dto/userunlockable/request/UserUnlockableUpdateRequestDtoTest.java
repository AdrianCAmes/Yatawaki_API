package com.orchestrator.orchestrator.model.dto.userunlockable.request;

import com.orchestrator.orchestrator.model.dto.DtoTest;
import com.orchestrator.orchestrator.model.dto.EqualsTester;
import org.junit.jupiter.api.Test;

public class UserUnlockableUpdateRequestDtoTest extends DtoTest<UserUnlockableUpdateRequestDto> {
    @Override
    protected UserUnlockableUpdateRequestDto getInstance() {
        return new UserUnlockableUpdateRequestDto();
    }

    @Test
    public void testEqualsAndHashCode() {
        UserUnlockableUpdateRequestDto userUnlockableUpdateRequestDtoTest = new UserUnlockableUpdateRequestDto();
        userUnlockableUpdateRequestDtoTest.setIdUnlockable(1L);

        UserUnlockableUpdateRequestDto userChangeRequestDto1 = new UserUnlockableUpdateRequestDto();
        userChangeRequestDto1.setIdUnlockable(2L);
        EqualsTester<UserUnlockableUpdateRequestDto> equalsTester = EqualsTester.newInstance( new UserUnlockableUpdateRequestDto() );
        equalsTester.assertEqual( new UserUnlockableUpdateRequestDto(), new UserUnlockableUpdateRequestDto() );
        equalsTester.assertNotEqual( userUnlockableUpdateRequestDtoTest, userChangeRequestDto1);
    }
}
