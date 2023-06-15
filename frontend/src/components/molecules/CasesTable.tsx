import { AuthContext } from "@/context/AuthContext";
import { Paper, Table, TableBody, TableCell, TableContainer, TableHead, TablePagination, TableRow, TableSortLabel } from "@mui/material";
import axios from "axios";
import React, { useContext, useEffect, useState } from "react";
import { ICase, TVisions, Visions } from "../organisms/ShowCasesPage.interfaces";
import { Allegation, IFiltersParams } from "./CasesTable.interface";

const headersLawyer = [
	{ key: 'case_id', label: 'ID do caso' },
	{ key: 'client_id', label: 'ID do cliente' },
	{ key: 'category', label: 'Categoria' },
	{ key: 'title', label: 'Título' },
	{ key: 'description', label: 'Descrição' },
	{ key: 'alegation', label: 'Alegação' },
]

const headersClient = [
	{ key: 'case_id', label: 'ID do caso' },
	{ key: 'lawyer_id', label: 'ID do advogado' },
	{ key: 'category', label: 'Categoria' },
	{ key: 'title', label: 'Título' },
	{ key: 'description', label: 'Descrição' },
	{ key: 'alegation', label: 'Alegação' },
]

export default function CasesTable ({ vision }: { vision: TVisions}) {
  const { token, loggedUser } = useContext(AuthContext);
	const [cases, setCases] = useState<ICase[]>([]);
  const [sortColumn, setSortColumn] = useState('case_id');
  const [sortOrder, setSortOrder] = useState<'asc' | 'desc'>('asc');
  const [rowsPerPage, setRowsPerPage] = useState(10);
  const [page, setPage] = useState(1);
  const [totalPages, setTotalPages] = useState(0);

  const axiosInstance = axios.create({
    baseURL: process.env.JUSTICE_FOR_ALL_API_URL,
    headers: { Authorization: `Basic ${token}` }
  })

  const handleChangePage = (event: unknown, newPage: number) => {
    setPage(newPage + 1);
  }

  const handleChangeRowsPerPage = (event: React.ChangeEvent<HTMLInputElement>) => {
    setRowsPerPage(parseInt(event.target.value, 10));
    setPage(1);
  }

  const handleSort = (column: string) => {
    if (column === sortColumn) {
      setSortOrder(sortOrder === 'asc' ? 'desc' : 'asc');
    } else {
      setSortColumn(column);
      setSortOrder('asc');
    }
  };

  useEffect(() => {
    const params: IFiltersParams = {
      page,
      size: rowsPerPage,
      sort_by: sortColumn,
      order_by: sortOrder,
    }
  
    if (vision === Visions.LAWYER_CASES) {
      params.lawyerId = loggedUser?.userId
    } else if (vision === Visions.OPENED_CASES) {
      params.opened = true
    } else if (vision === Visions.CLIENT_CASES) {
      params.clientId = loggedUser?.userId
    }

    axiosInstance.get('/case', {
      params
    })
      .then((res) => {
        if (res.status === 200) {
          setCases(res.data.cases)
          setTotalPages(res.data.totalPages)
        }
      }).catch(err => {
        console.error(err)
        alert('Erro ao buscar casos')
      })
  }, [vision, page, rowsPerPage, sortColumn, sortOrder])

  return (
    <>
      <TableContainer component={Paper}>
        <Table sx={{ minWidth: 650 }} aria-label="simple table">
          <TableHead>
            <TableRow>
              {(vision === Visions.CLIENT_CASES ? headersClient : headersLawyer).map(({ key, label }) => (
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
            </TableRow>
          </TableHead>
          <TableBody>
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
                <TableCell>{_case.alegation === Allegation.GUITY ? 'Culpado' : 'Inocente' }</TableCell>
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
    </>
  )
}
