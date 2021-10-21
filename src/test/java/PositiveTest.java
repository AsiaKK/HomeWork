import org.testng.annotations.*;
import selenium.base.TestBase;
import selenium.pages.*;

import static org.assertj.core.api.Assertions.assertThat;

public class PositiveTest extends TestBase {

    RegisteredOwnerPage registeredOwnerPage;
    VehiclePage vehiclePage;
    ModelPage modelPage;
    BodyTypePage bodyTypePage;
    FuelTypePage fuelTypePage;
    EnginePowerPage enginePowerPage;
    EnginePage enginePage;
    RegistrationDatePage registrationDatePage;
    BirthDatePage birthDatePage;

    private static final String BRAND = "OPEL";

    @Test
    public void checkDefaultSetUp() {
        assertThat(preconditionPage.checkIsCarAlreadyInsuredSelected()).isTrue();
        assertThat(preconditionPage.getPageLocator()).isEqualTo("01.01.2022");

        registeredOwnerPage = preconditionPage
                .goToCarOwnerPage();
        assertThat(registeredOwnerPage.checkIsYesForOwnerTheCarSelected()).isTrue();
        assertThat(registeredOwnerPage.checkIsUsedWhenBuyingSelected()).isTrue();
    }

    @Test
    public void checkTextsOnPages() {
        preconditionPage
                .checkTextsForPreconditionPage()
                .fillInsuranceStartDate("01.11.2021");
        registeredOwnerPage = preconditionPage
                .goToCarOwnerPage();
        registeredOwnerPage
                .checkTextsOnCarOwnerPage();
        vehiclePage = registeredOwnerPage
                .goToVehiclePage();
        vehiclePage
                .checkTextsForHsnTsnSite()
                .clickOnBrandAndModelButton()
                .checkTextsForBrandAndModelSite()
                .enterCarBrand(BRAND);
        modelPage = vehiclePage
                .clickOnBrand(BRAND);
        modelPage
                .checkTextsForCarModelPage()
                .enterModel("Astra");
        bodyTypePage = modelPage
                .clickOnModel();
        bodyTypePage
                .checkTextOnShapeCarPage();
        fuelTypePage = bodyTypePage
                .chooseBodyType("limousine");
        enginePowerPage = fuelTypePage
                .chooseFuelType("petrol");
        enginePage = enginePowerPage
                .enterEnginePower("40 kW / 54 PS");
        registrationDatePage = enginePage
                .clickOnEngine("ASTRA-F STH");
        registrationDatePage
                .checkTextsOnRegistrationDatePage()
                .enterFirstRegistrationDate("12.2019")
                .enterOwnerRegistrationDate("02.2020");
        birthDatePage = registrationDatePage
                .goToBornDatePage();
    }

    @Test(dataProvider = "valueCombination")
    public void fillTheInsuredVehicleForm(String startDate, String brand, String model, String bodyType, String fuelType, String enginePower, String engine, String firstRegistrationDate, String ownerRegistrationDate) {
        preconditionPage
                .fillInsuranceStartDate(startDate);
        registeredOwnerPage = preconditionPage
                .goToCarOwnerPage();
        vehiclePage = registeredOwnerPage
                .goToVehiclePage();
        vehiclePage
                .clickOnBrandAndModelButton()
                .checkBrandAndModelButtonSelected()
                .enterCarBrand(brand);
        modelPage = vehiclePage
                .clickOnBrand(brand);
        modelPage
                .enterModel(model);
        bodyTypePage = modelPage
                .clickOnModel();
        bodyTypePage
                .checkTextOnShapeCarPage();
        fuelTypePage = bodyTypePage
                .chooseBodyType(bodyType);
        enginePowerPage = fuelTypePage
                .chooseFuelType(fuelType);
        enginePage = enginePowerPage
                .enterEnginePower(enginePower);
        registrationDatePage = enginePage
                .clickOnEngine(engine);
        registrationDatePage
                .checkTextsOnRegistrationDatePage()
                .enterFirstRegistrationDate(firstRegistrationDate)
                .enterOwnerRegistrationDate(ownerRegistrationDate);
        birthDatePage = registrationDatePage
                .goToBornDatePage();
    }

    @DataProvider(name = "valueCombination")
    private Object[][] createData() {
        return new Object[][]{
                {"01.02.2022", "BMW", "1 series", "limousine", "petrol", "100 kW / 136 PS", "HSN: 0005, TSN: CAK", "12.1999", "01.2000"},
                {"01.03.2022", "FORD", "Focus", "limousine", "diesel", "55 kW / 75 PS", "HSN: 8566, TSN: 377", "11.2016", "01.2017"},
                {"01.04.2022", "AUDI", "A4", "limousine", "petrol", "74 kW / 101 PS", "HSN: 0588, TSN: 591", "01.2019", "10.2020"},
        };
    }
}
