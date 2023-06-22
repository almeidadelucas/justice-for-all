import CreateCasePage from '@/components/organisms/CreateCasePage';
import { withAuth } from '@/components/templates/Auth';
import React from 'react';

function CreateCase() {
	return <CreateCasePage/>
}

export default withAuth(CreateCase);