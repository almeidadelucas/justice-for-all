import { Box } from '@mui/material';
import React, { useContext, useState } from 'react';
import { AuthContext } from '@/context/AuthContext';
import CasesTable from '../molecules/CasesTable';
import SwitchLawyerVision from '../molecules/SwitchLawyerVision';
import { CLIENT_FILTERS, LAWYER_FILTERS, TVisions, UserTypes, Visions } from './ShowCasesPage.interfaces';
import { FilterBy } from '../molecules/FilterBy';

export default function ShowCasesPage() {
  const { loggedUser } = useContext(AuthContext);
  const [vision, setVision] = useState<TVisions>(
    (loggedUser?.type === 'LAWYER' ? Visions.LAWYER_CASES : Visions.CLIENT_CASES) as TVisions
  )
  const [filterKey, setFilterKey] = useState('');
  const [filterValue, setFilterValue] = useState('');

	return (
    <Box display="flex" flexDirection="column" width="100%">
      <Box
        sx={{
          display: "flex",
          alignItems: "center",
          border: "1px solid #a8a7a7",
          padding: "0.5rem 1rem",
          borderRadius: "15px",
          marginBottom: '1rem',
          justifyContent: 'space-between'
        }}
      >
        {loggedUser?.type === UserTypes.LAWYER && (
          <SwitchLawyerVision
            checked={vision === Visions.OPENED_CASES}
            handleChange={(value) => {
              const newVision = (value ? Visions.OPENED_CASES : Visions.LAWYER_CASES) as keyof typeof Visions;
              setVision(newVision)
            }}
          />
        )}
        <FilterBy
          options={loggedUser?.type === UserTypes.LAWYER ? LAWYER_FILTERS : CLIENT_FILTERS}
          onSearch={(key, value) => {
            setFilterKey(key);
            setFilterValue(value);
          }}
        />
      </Box>
      <CasesTable vision={vision} filterKey={filterKey} filterValue={filterValue} />
    </Box>
	);
}
