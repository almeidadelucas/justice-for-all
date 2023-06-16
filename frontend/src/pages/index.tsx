import { CenteredBox } from '@/components/atoms/CenteredBox';
import { withAuth } from '@/components/templates/Auth';
import { Typography } from '@mui/material';
import { useRouter } from 'next/router';
import React from 'react';

function Home() {
	const { push } = useRouter();

	return (
		<CenteredBox>
			<Typography>Boas vindas!</Typography>
		</CenteredBox>
	);
}

export default withAuth(Home);
