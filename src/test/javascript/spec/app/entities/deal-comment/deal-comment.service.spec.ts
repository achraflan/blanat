import { TestBed, getTestBed } from '@angular/core/testing';
import { HttpClientTestingModule, HttpTestingController } from '@angular/common/http/testing';
import * as moment from 'moment';
import { DATE_FORMAT } from 'app/shared/constants/input.constants';
import { DealCommentService } from 'app/entities/deal-comment/deal-comment.service';
import { IDealComment, DealComment } from 'app/shared/model/deal-comment.model';

describe('Service Tests', () => {
  describe('DealComment Service', () => {
    let injector: TestBed;
    let service: DealCommentService;
    let httpMock: HttpTestingController;
    let elemDefault: IDealComment;
    let expectedResult: IDealComment | IDealComment[] | boolean | null;
    let currentDate: moment.Moment;

    beforeEach(() => {
      TestBed.configureTestingModule({
        imports: [HttpClientTestingModule],
      });
      expectedResult = null;
      injector = getTestBed();
      service = injector.get(DealCommentService);
      httpMock = injector.get(HttpTestingController);
      currentDate = moment();

      elemDefault = new DealComment(0, 'AAAAAAA', currentDate, false, false, 0, 0, currentDate);
    });

    describe('Service methods', () => {
      it('should find an element', () => {
        const returnedFromService = Object.assign(
          {
            dateComment: currentDate.format(DATE_FORMAT),
            dateLastModification: currentDate.format(DATE_FORMAT),
          },
          elemDefault
        );

        service.find(123).subscribe(resp => (expectedResult = resp.body));

        const req = httpMock.expectOne({ method: 'GET' });
        req.flush(returnedFromService);
        expect(expectedResult).toMatchObject(elemDefault);
      });

      it('should create a DealComment', () => {
        const returnedFromService = Object.assign(
          {
            id: 0,
            dateComment: currentDate.format(DATE_FORMAT),
            dateLastModification: currentDate.format(DATE_FORMAT),
          },
          elemDefault
        );

        const expected = Object.assign(
          {
            dateComment: currentDate,
            dateLastModification: currentDate,
          },
          returnedFromService
        );

        service.create(new DealComment()).subscribe(resp => (expectedResult = resp.body));

        const req = httpMock.expectOne({ method: 'POST' });
        req.flush(returnedFromService);
        expect(expectedResult).toMatchObject(expected);
      });

      it('should update a DealComment', () => {
        const returnedFromService = Object.assign(
          {
            comment: 'BBBBBB',
            dateComment: currentDate.format(DATE_FORMAT),
            isActive: true,
            isDeleted: true,
            like: 1,
            dislike: 1,
            dateLastModification: currentDate.format(DATE_FORMAT),
          },
          elemDefault
        );

        const expected = Object.assign(
          {
            dateComment: currentDate,
            dateLastModification: currentDate,
          },
          returnedFromService
        );

        service.update(expected).subscribe(resp => (expectedResult = resp.body));

        const req = httpMock.expectOne({ method: 'PUT' });
        req.flush(returnedFromService);
        expect(expectedResult).toMatchObject(expected);
      });

      it('should return a list of DealComment', () => {
        const returnedFromService = Object.assign(
          {
            comment: 'BBBBBB',
            dateComment: currentDate.format(DATE_FORMAT),
            isActive: true,
            isDeleted: true,
            like: 1,
            dislike: 1,
            dateLastModification: currentDate.format(DATE_FORMAT),
          },
          elemDefault
        );

        const expected = Object.assign(
          {
            dateComment: currentDate,
            dateLastModification: currentDate,
          },
          returnedFromService
        );

        service.query().subscribe(resp => (expectedResult = resp.body));

        const req = httpMock.expectOne({ method: 'GET' });
        req.flush([returnedFromService]);
        httpMock.verify();
        expect(expectedResult).toContainEqual(expected);
      });

      it('should delete a DealComment', () => {
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
