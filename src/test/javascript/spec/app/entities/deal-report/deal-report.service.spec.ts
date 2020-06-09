import { TestBed, getTestBed } from '@angular/core/testing';
import { HttpClientTestingModule, HttpTestingController } from '@angular/common/http/testing';
import * as moment from 'moment';
import { DATE_FORMAT } from 'app/shared/constants/input.constants';
import { DealReportService } from 'app/entities/deal-report/deal-report.service';
import { IDealReport, DealReport } from 'app/shared/model/deal-report.model';

describe('Service Tests', () => {
  describe('DealReport Service', () => {
    let injector: TestBed;
    let service: DealReportService;
    let httpMock: HttpTestingController;
    let elemDefault: IDealReport;
    let expectedResult: IDealReport | IDealReport[] | boolean | null;
    let currentDate: moment.Moment;

    beforeEach(() => {
      TestBed.configureTestingModule({
        imports: [HttpClientTestingModule],
      });
      expectedResult = null;
      injector = getTestBed();
      service = injector.get(DealReportService);
      httpMock = injector.get(HttpTestingController);
      currentDate = moment();

      elemDefault = new DealReport(0, 'AAAAAAA', currentDate, currentDate, false);
    });

    describe('Service methods', () => {
      it('should find an element', () => {
        const returnedFromService = Object.assign(
          {
            dateReport: currentDate.format(DATE_FORMAT),
            dateClose: currentDate.format(DATE_FORMAT),
          },
          elemDefault
        );

        service.find(123).subscribe(resp => (expectedResult = resp.body));

        const req = httpMock.expectOne({ method: 'GET' });
        req.flush(returnedFromService);
        expect(expectedResult).toMatchObject(elemDefault);
      });

      it('should create a DealReport', () => {
        const returnedFromService = Object.assign(
          {
            id: 0,
            dateReport: currentDate.format(DATE_FORMAT),
            dateClose: currentDate.format(DATE_FORMAT),
          },
          elemDefault
        );

        const expected = Object.assign(
          {
            dateReport: currentDate,
            dateClose: currentDate,
          },
          returnedFromService
        );

        service.create(new DealReport()).subscribe(resp => (expectedResult = resp.body));

        const req = httpMock.expectOne({ method: 'POST' });
        req.flush(returnedFromService);
        expect(expectedResult).toMatchObject(expected);
      });

      it('should update a DealReport', () => {
        const returnedFromService = Object.assign(
          {
            reason: 'BBBBBB',
            dateReport: currentDate.format(DATE_FORMAT),
            dateClose: currentDate.format(DATE_FORMAT),
            isValid: true,
          },
          elemDefault
        );

        const expected = Object.assign(
          {
            dateReport: currentDate,
            dateClose: currentDate,
          },
          returnedFromService
        );

        service.update(expected).subscribe(resp => (expectedResult = resp.body));

        const req = httpMock.expectOne({ method: 'PUT' });
        req.flush(returnedFromService);
        expect(expectedResult).toMatchObject(expected);
      });

      it('should return a list of DealReport', () => {
        const returnedFromService = Object.assign(
          {
            reason: 'BBBBBB',
            dateReport: currentDate.format(DATE_FORMAT),
            dateClose: currentDate.format(DATE_FORMAT),
            isValid: true,
          },
          elemDefault
        );

        const expected = Object.assign(
          {
            dateReport: currentDate,
            dateClose: currentDate,
          },
          returnedFromService
        );

        service.query().subscribe(resp => (expectedResult = resp.body));

        const req = httpMock.expectOne({ method: 'GET' });
        req.flush([returnedFromService]);
        httpMock.verify();
        expect(expectedResult).toContainEqual(expected);
      });

      it('should delete a DealReport', () => {
        service.delete(123).subscribe(resp => (expectedResult = resp.ok));

        const req = httpMock.expectOne({ method: 'DELETE' });
        req.flush({ status: 200 });
        expect(expectedResult);
      });
    });

    afterEach(() => {
      httpMock.verify();
    });
  });
});
