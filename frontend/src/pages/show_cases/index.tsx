import ShowCasesPage from '@/components/organisms/ShowCasesPage';
import { withAuth } from '@/components/templates/Auth';
import React from 'react';

function ShowCases() {
	return <ShowCasesPage type="LAWYER" />;
}

export default withAuth(ShowCases);
