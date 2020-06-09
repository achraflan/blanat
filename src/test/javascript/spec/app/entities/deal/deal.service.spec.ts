import { TestBed, getTestBed } from '@angular/core/testing';
import { HttpClientTestingModule, HttpTestingController } from '@angular/common/http/testing';
import * as moment from 'moment';
import { DATE_FORMAT } from 'app/shared/constants/input.constants';
import { DealService } from 'app/entities/deal/deal.service';
import { IDeal, Deal } from 'app/shared/model/deal.model';
import { TypeDeal } from 'app/shared/model/enumerations/type-deal.model';
import { TypeCoupon } from 'app/shared/model/enumerations/type-coupon.model';
import { StatutDeal } from 'app/shared/model/enumerations/statut-deal.model';

describe('Service Tests', () => {
  describe('Deal Service', () => {
    let injector: TestBed;
    let service: DealService;
    let httpMock: HttpTestingController;
    let elemDefault: IDeal;
    let expectedResult: IDeal | IDeal[] | boolean | null;
    let currentDate: moment.Moment;

    beforeEach(() => {
      TestBed.configureTestingModule({
        imports: [HttpClientTestingModule],
      });
      expectedResult = null;
      injector = getTestBed();
      service = injector.get(DealService);
      httpMock = injector.get(HttpTestingController);
      currentDate = moment();

      elemDefault = new Deal(
        0,
        'AAAAAAA',
        'AAAAAAA',
        TypeDeal.DEAL,
        'AAAAAAA',
        'AAAAAAA',
        0,
        0,
        0,
        'AAAAAAA',
        TypeCoupon.PERCENTAGE,
        0,
        0,
        currentDate,
        currentDate,
        currentDate,
        0,
        0,
        0,
        false,
        StatutDeal.ACTIVE,
        false,
        false
      );
    });

    describe('Service methods', () => {
      it('should find an element', () => {
        const returnedFromService = Object.assign(
          {
            dateStart: currentDate.format(DATE_FORMAT),
            dateEnd: currentDate.format(DATE_FORMAT),
            datePublication: currentDate.format(DATE_FORMAT),
          },
          elemDefault
        );

        service.find(123).subscribe(resp => (expectedResult = resp.body));

        const req = httpMock.expectOne({ method: 'GET' });
        req.flush(returnedFromService);
        expect(expectedResult).toMatchObject(elemDefault);
      });

      it('should create a Deal', () => {
        const returnedFromService = Object.assign(
          {
            id: 0,
            dateStart: currentDate.format(DATE_FORMAT),
            dateEnd: currentDate.format(DATE_FORMAT),
            datePublication: currentDate.format(DATE_FORMAT),
          },
          elemDefault
        );

        const expected = Object.assign(
          {
            dateStart: currentDate,
            dateEnd: currentDate,
            datePublication: currentDate,
          },
          returnedFromService
        );

        service.create(new Deal()).subscribe(resp => (expectedResult = resp.body));

        const req = httpMock.expectOne({ method: 'POST' });
        req.flush(returnedFromService);
        expect(expectedResult).toMatchObject(expected);
      });

      it('should update a Deal', () => {
        const returnedFromService = Object.assign(
          {
            title: 'BBBBBB',
            description: 'BBBBBB',
            type: 'BBBBBB',
            url: 'BBBBBB',
            image: 'BBBBBB',
            price: 1,
            priceNormal: 1,
            priceShipping: 1,
            coupon: 'BBBBBB',
            couponType: 'BBBBBB',
            couponValue: 1,
            couponPercentage: 1,
            dateStart: currentDate.format(DATE_FORMAT),
            dateEnd: currentDate.format(DATE_FORMAT),
            datePublication: currentDate.format(DATE_FORMAT),
            views: 1,
            like: 1,
            dislike: 1,
            local: true,
            statut: 'BBBBBB',
            isDeleted: true,
            isBlocked: true,
          },
          elemDefault
        );

        const expected = Object.assign(
          {
            dateStart: currentDate,
            dateEnd: currentDate,
            datePublication: currentDate,
          },
          returnedFromService
        );

        service.update(expected).subscribe(resp => (expectedResult = resp.body));

        const req = httpMock.expectOne({ method: 'PUT' });
        req.flush(returnedFromService);
        expect(expectedResult).toMatchObject(expected);
      });

      it('should return a list of Deal', () => {
        const returnedFromService = Object.assign(
          {
            title: 'BBBBBB',
            description: 'BBBBBB',
            type: 'BBBBBB',
            url: 'BBBBBB',
            image: 'BBBBBB',
            price: 1,
            priceNormal: 1,
            priceShipping: 1,
            coupon: 'BBBBBB',
            couponType: 'BBBBBB',
            couponValue: 1,
            couponPercentage: 1,
            dateStart: currentDate.format(DATE_FORMAT),
            dateEnd: currentDate.format(DATE_FORMAT),
            datePublication: currentDate.format(DATE_FORMAT),
            views: 1,
            like: 1,
            dislike: 1,
            local: true,
            statut: 'BBBBBB',
            isDeleted: true,
            isBlocked: true,
          },
          elemDefault
        );

        const expected = Object.assign(
          {
            dateStart: currentDate,
            dateEnd: currentDate,
            datePublication: currentDate,
          },
          returnedFromService
        );

        service.query().subscribe(resp => (expectedResult = resp.body));

        const req = httpMock.expectOne({ method: 'GET' });
        req.flush([returnedFromService]);
        httpMock.verify();
        expect(expectedResult).toContainEqual(expected);
      });

      it('should delete a Deal', () => {
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
