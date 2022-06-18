package com.orchestrator.orchestrator.model.dto.userunlockable.request;

import com.orchestrator.orchestrator.model.dto.DtoTest;
import com.orchestrator.orchestrator.model.dto.EqualsTester;
import org.junit.jupiter.api.Test;

public class UserUnlockableCreateRequestDtoTest extends DtoTest<UserUnlockableCreateRequestDto> {
    @Override
    protected UserUnlockableCreateRequestDto getInstance() {
        return new UserUnlockableCreateRequestDto();
    }

    @Test
    public void testEqualsAndHashCode() {
        UserUnlockableCreateRequestDto userUnlockableCreateRequestDtoTest = new UserUnlockableCreateRequestDto();
        userUnlockableCreateRequestDtoTest.setIdUnlockable(1L);

        UserUnlockableCreateRequestDto userChangeRequestDto1 = new UserUnlockableCreateRequestDto();
        userChangeRequestDto1.setIdUnlockable(2L);
        EqualsTester<UserUnlockableCreateRequestDto> equalsTester = EqualsTester.newInstance( new UserUnlockableCreateRequestDto() );
        equalsTester.assertEqual( new UserUnlockableCreateRequestDto(), new UserUnlockableCreateRequestDto() );
        equalsTester.assertNotEqual( userUnlockableCreateRequestDtoTest, userChangeRequestDto1);
    }
}