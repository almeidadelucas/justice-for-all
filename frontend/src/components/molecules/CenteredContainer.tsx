import React from 'react';
import { Typography } from '@mui/material';
import { BorderedBox } from '@/components/atoms/BorderedBox';
import { CenteredBox } from '@/components/atoms/CenteredBox';

interface ICenteredContainerProps {
  title: string;
  children: JSX.Element;
}

export function CenteredContainer({ title, children }: ICenteredContainerProps) {
	return (
		<CenteredBox>
			<BorderedBox flexDirection="column">
				<Typography variant="h4" component="h1" sx={{ marginBottom: '1rem' }}>
					{title}
				</Typography>
				{children}
			</BorderedBox>
		</CenteredBox>
	);
}