import { TestBed, getTestBed } from '@angular/core/testing';
import { HttpClientTestingModule, HttpTestingController } from '@angular/common/http/testing';
import * as moment from 'moment';
import { DATE_FORMAT } from 'app/shared/constants/input.constants';
import { DealUserService } from 'app/entities/deal-user/deal-user.service';
import { IDealUser, DealUser } from 'app/shared/model/deal-user.model';
import { Gender } from 'app/shared/model/enumerations/gender.model';

describe('Service Tests', () => {
  describe('DealUser Service', () => {
    let injector: TestBed;
    let service: DealUserService;
    let httpMock: HttpTestingController;
    let elemDefault: IDealUser;
    let expectedResult: IDealUser | IDealUser[] | boolean | null;
    let currentDate: moment.Moment;

    beforeEach(() => {
      TestBed.configureTestingModule({
        imports: [HttpClientTestingModule],
      });
      expectedResult = null;
      injector = getTestBed();
      service = injector.get(DealUserService);
      httpMock = injector.get(HttpTestingController);
      currentDate = moment();

      elemDefault = new DealUser(0, Gender.MALE, 'AAAAAAA', 'AAAAAAA', 'AAAAAAA', currentDate, false, false, false, false, false);
    });

    describe('Service methods', () => {
      it('should find an element', () => {
        const returnedFromService = Object.assign(
          {
            birthDay: currentDate.format(DATE_FORMAT),
          },
          elemDefault
        );

        service.find(123).subscribe(resp => (expectedResult = resp.body));

        const req = httpMock.expectOne({ method: 'GET' });
        req.flush(returnedFromService);
        expect(expectedResult).toMatchObject(elemDefault);
      });

      it('should create a DealUser', () => {
        const returnedFromService = Object.assign(
          {
            id: 0,
            birthDay: currentDate.format(DATE_FORMAT),
          },
          elemDefault
        );

        const expected = Object.assign(
          {
            birthDay: currentDate,
          },
          returnedFromService
        );

        service.create(new DealUser()).subscribe(resp => (expectedResult = resp.body));

        const req = httpMock.expectOne({ method: 'POST' });
        req.flush(returnedFromService);
        expect(expectedResult).toMatchObject(expected);
      });

      it('should update a DealUser', () => {
        const returnedFromService = Object.assign(
          {
            gender: 'BBBBBB',
            phone: 'BBBBBB',
            address: 'BBBBBB',
            city: 'BBBBBB',
            birthDay: currentDate.format(DATE_FORMAT),
            comment: true,
            notification: true,
            reporting: true,
            emailNotification: true,
            message: true,
          },
          elemDefault
        );

        const expected = Object.assign(
          {
            birthDay: currentDate,
          },
          returnedFromService
        );

        service.update(expected).subscribe(resp => (expectedResult = resp.body));

        const req = httpMock.expectOne({ method: 'PUT' });
        req.flush(returnedFromService);
        expect(expectedResult).toMatchObject(expected);
      });

      it('should return a list of DealUser', () => {
        const returnedFromService = Object.assign(
          {
            gender: 'BBBBBB',
            phone: 'BBBBBB',
            address: 'BBBBBB',
            city: 'BBBBBB',
            birthDay: currentDate.format(DATE_FORMAT),
            comment: true,
            notification: true,
            reporting: true,
            emailNotification: true,
            message: true,
          },
          elemDefault
        );

        const expected = Object.assign(
          {
            birthDay: currentDate,
          },
          returnedFromService
        );

        service.query().subscribe(resp => (expectedResult = resp.body));

        const req = httpMock.expectOne({ method: 'GET' });
        req.flush([returnedFromService]);
        httpMock.verify();
        expect(expectedResult).toContainEqual(expected);
      });

      it('should delete a DealUser', () => {
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
