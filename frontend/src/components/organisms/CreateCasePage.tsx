import React from 'react';
import { CreateCaseForm } from '../molecules/CreateCaseForm';
import { CenteredContainer } from '@/components/molecules/CenteredContainer';
import { Box } from '@mui/material';
import { EditCaseForm } from '../molecules/EditCaseForm';

export default function CreateCasePage() {
	return (
		<Box
        sx={{
          display: 'flex',
          flexDirection: 'column',
          justifyContent: 'center',
          alignItems: 'center',
          rowGap: '2rem',
          width: '100%'
        }}>
			<CreateCaseForm/>
		</Box>
	);
}