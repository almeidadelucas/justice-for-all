export const HEADERS_LAWYER = [
  { key: 'case_id', label: 'ID do caso' },
  { key: 'user_id', label: 'ID do cliente' },
  { key: 'category', label: 'Categoria' },
  { key: 'title', label: 'Título' },
  { key: 'description', label: 'Descrição' },
  { key: 'alegation', label: 'Alegação' },
];

export const HEADERS_CLIENT = [
  { key: 'case_id', label: 'ID do caso' },
  { key: 'lawyer_id', label: 'ID do advogado' },
  { key: 'category', label: 'Categoria' },
  { key: 'title', label: 'Título' },
  { key: 'description', label: 'Descrição' },
  { key: 'alegation', label: 'Alegação' },
];

export const Allegation = {
  INNOCENT: 'INNOCENT',
  GUITY: 'GUITY',
};

export interface IFiltersParams {
  page?: number;
  size?: number;
  userId?: number;
  lawyerId?: number;
  open?: boolean;
  sort_by?: string;
  order_by?: 'asc' | 'desc';
}

export interface ICase {
  caseId: number;
  userId: number;
  lawyerId: number;
  title: string;
  category: string;
  description: string;
  alegation: keyof typeof Allegation;
  evidencesPDF: string;
  evidencesImages: string;
  caseIdentifier: string;
  open: boolean;
}
