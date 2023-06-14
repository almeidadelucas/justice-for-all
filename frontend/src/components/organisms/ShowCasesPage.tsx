import { Box } from '@mui/material';
import React, { useContext, useState } from 'react';
import { AuthContext } from '@/context/AuthContext';
import CasesTable from '../molecules/CasesTable';
import SwitchLawyerVision from '../molecules/SwitchLawyerVision';
import { TVisions, UserTypes, Visions } from './ShowCasesPage.interfaces';

export default function ShowCasesPage() {
  const { loggedUser } = useContext(AuthContext);
  const [vision, setVision] = useState<TVisions>(
    (loggedUser?.type === 'LAWYER' ? Visions.LAWYER_CASES : Visions.CLIENT_CASES) as TVisions
  )

	return (
    <Box display="flex" flexDirection="column" width="100%">
      {loggedUser?.type === UserTypes.LAWYER && (
        <SwitchLawyerVision
          checked={vision === Visions.OPENED_CASES}
          handleChange={(value) => {
            const newVision = (value ? Visions.OPENED_CASES : Visions.LAWYER_CASES) as keyof typeof Visions;
            setVision(newVision)
          }}
        />
      )}
      <CasesTable vision={vision} />
    </Box>
	);
}
