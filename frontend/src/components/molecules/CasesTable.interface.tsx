export const Allegation = {
	INNOCENT: 'INNOCENT',
	GUITY: 'GUITY'
}

export interface IFiltersParams {
  page?: number;
  size?: number;
  clientId?: number;
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
