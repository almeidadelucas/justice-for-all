import { CenteredBox } from '@/components/atoms/CenteredBox';
import { withAuth } from '@/components/templates/Auth';
import { Typography } from '@mui/material';
import React from 'react';

function Home() {
  return (
    <CenteredBox>
      <Typography>Boas vindas!</Typography>
    </CenteredBox>
  );
}

export default withAuth(Home);
