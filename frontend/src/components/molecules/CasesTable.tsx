import { AuthContext } from '@/context/AuthContext';
import {
  IconButton,
  Paper,
  Table,
  TableBody,
  TableCell,
  TableContainer,
  TableHead,
  TablePagination,
  TableRow,
  TableSortLabel,
  Tooltip,
  Typography,
} from '@mui/material';
import axios from 'axios';
import React, { useContext, useEffect, useState } from 'react';
import {
  ICase,
  TVisions,
  Visions,
} from '../organisms/ShowCasesPage.interfaces';
import {
  Allegation,
  HEADERS_CLIENT,
  HEADERS_LAWYER,
  IFiltersParams,
} from './CasesTable.interface';
import AdsClickIcon from '@mui/icons-material/AdsClick';
import VisibilityIcon from '@mui/icons-material/Visibility';
import ProposalModal from './ProposalModal';
import ReceivedProposalModal from './ReceivedProposalModal';
import InfoOutlinedIcon from '@mui/icons-material/InfoOutlined';

export default function CasesTable({
  vision,
  filterKey,
  filterValue,
}: {
  vision: TVisions;
  filterKey: string;
  filterValue: string;
}) {
  const { token, loggedUser } = useContext(AuthContext);

  const [cases, setCases] = useState<ICase[]>([]);
  const [sortColumn, setSortColumn] = useState('case_id');
  const [sortOrder, setSortOrder] = useState<'asc' | 'desc'>('asc');
  const [rowsPerPage, setRowsPerPage] = useState(5);
  const [page, setPage] = useState(1);
  const [totalPages, setTotalPages] = useState(0);
  const [rawSearch, setRawSearch] = useState({});

  const [caseId, setCaseId] = useState<number>();
  const [showProposalModal, setShowProposalModal] = useState(false);
  const [showReceivedProposalModal, setShowReceivedProposalModal] =
    useState(false);

  const axiosInstance = axios.create({
    baseURL: process.env.JUSTICE_FOR_ALL_API_URL,
    headers: { Authorization: `Basic ${token}` },
  });

  const handleChangePage = (event: unknown, newPage: number) => {
    setPage(newPage + 1);
  };

  const handleChangeRowsPerPage = (
    event: React.ChangeEvent<HTMLInputElement>
  ) => {
    setRowsPerPage(parseInt(event.target.value, 10));
    setPage(1);
  };

  const handleSort = (column: string) => {
    if (column === sortColumn) {
      setSortOrder(sortOrder === 'asc' ? 'desc' : 'asc');
    } else {
      setSortColumn(column);
      setSortOrder('asc');
    }
  };

  const fetchCases = async () => {
    const params: IFiltersParams = {
      page,
      size: rowsPerPage,
      sort_by: sortColumn,
      order_by: sortOrder,
      ...rawSearch,
    };

    if (vision === Visions.LAWYER_CASES) {
      params.lawyerId = loggedUser?.userId;
    } else if (vision === Visions.OPENED_CASES) {
      params.open = true;
    } else if (vision === Visions.CLIENT_CASES) {
      params.userId = loggedUser?.userId;
    }

    try {
      const res = await axiosInstance.get('/case', {
        params,
      });

      if (res.status === 200) {
        setCases(res.data.cases);
        setTotalPages(res.data.totalPages);
      }
    } catch (err) {
      console.log(err);
      alert('Erro ao buscar casos');
    }
  };

  useEffect(() => {
    fetchCases();
  }, [vision, page, rowsPerPage, sortColumn, sortOrder, rawSearch]);

  useEffect(() => {
    if (filterKey && filterValue) {
      setRawSearch({ [filterKey]: filterValue });
    } else {
      setRawSearch({});
    }
    setPage(1);
  }, [filterKey, filterValue]);

  return (
    <>
      <TableContainer component={Paper}>
        <Table sx={{ minWidth: 650 }} aria-label="simple table">
          <TableHead>
            <TableRow>
              {(vision === Visions.CLIENT_CASES
                ? HEADERS_CLIENT
                : HEADERS_LAWYER
              ).map(({ key, label }) => (
                <TableCell key={key}>
                  <TableSortLabel
                    active={sortColumn === key}
                    direction={sortColumn === key ? sortOrder : 'asc'}
                    onClick={() => handleSort(key)}
                  >
                    {label}
                  </TableSortLabel>
                </TableCell>
              ))}
              <TableCell>Ações</TableCell>
            </TableRow>
          </TableHead>
          <TableBody>
            {cases.length === 0 && (
              <TableRow>
                <TableCell colSpan={7}>
                  <Typography variant="body1" align="center">
                    Nenhum caso encontrado
                  </Typography>
                </TableCell>
              </TableRow>
            )}
            {cases.map((_case: ICase) => (
              <TableRow
                key={_case.caseId}
                sx={{ '&:last-child td, &:last-child th': { border: 0 } }}
              >
                <TableCell component="th" scope="row">
                  {_case.caseId}
                </TableCell>
                <TableCell>{_case.userId}</TableCell>
                <TableCell>{_case.category}</TableCell>
                <TableCell>{_case.title}</TableCell>
                <TableCell>{_case.description}</TableCell>
                <TableCell>
                  {_case.alegation === Allegation.GUITY
                    ? 'Culpado'
                    : 'Inocente'}
                </TableCell>
                <TableCell>
                  {vision === Visions.CLIENT_CASES && _case.open && (
                    <Tooltip title="Visualizar propostas">
                      <IconButton
                        type="button"
                        sx={{ p: '10px' }}
                        onClick={() => {
                          setCaseId(_case.caseId);
                          setShowReceivedProposalModal(true);
                        }}
                      >
                        <VisibilityIcon />
                      </IconButton>
                    </Tooltip>
                  )}
                  {vision === Visions.CLIENT_CASES && !_case.open && (
                    <Tooltip title="Proposta aceita">
                      <IconButton type="button" sx={{ p: '10px' }}>
                        <InfoOutlinedIcon />
                      </IconButton>
                    </Tooltip>
                  )}
                  {vision === Visions.OPENED_CASES && (
                    <Tooltip title="Enviar proposta">
                      <IconButton
                        type="button"
                        sx={{ p: '10px' }}
                        onClick={() => {
                          setCaseId(_case.caseId);
                          setShowProposalModal(true);
                        }}
                      >
                        <AdsClickIcon />
                      </IconButton>
                    </Tooltip>
                  )}
                </TableCell>
              </TableRow>
            ))}
          </TableBody>
        </Table>
      </TableContainer>
      <TablePagination
        rowsPerPageOptions={[5, 10, 25]}
        component="div"
        count={totalPages * rowsPerPage}
        rowsPerPage={rowsPerPage}
        page={page - 1}
        onPageChange={handleChangePage}
        onRowsPerPageChange={handleChangeRowsPerPage}
      />
      <ProposalModal
        caseId={caseId}
        open={showProposalModal}
        onClose={() => setShowProposalModal(false)}
      />
      <ReceivedProposalModal
        caseId={caseId}
        open={showReceivedProposalModal}
        onClose={() => {
          setShowReceivedProposalModal(false);
          fetchCases();
        }}
      />
    </>
  );
}
