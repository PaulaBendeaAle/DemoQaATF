package tests;

import actions.AccountActions;
import actions.BookStoreActions;
import extentUtility.ExtentUtility;
import extentUtility.ReportStep;
import hooks.Hooks;
import objectData.requestObject.RequestAccount;
import objectData.requestObject.RequestAccountBook;
import objectData.requestObject.RequestAccountBooks;
import objectData.responseObject.ResponseAccountSuccess;
import objectData.responseObject.ResponseTokenSuccess;
import org.testng.annotations.Test;
import propertyUtility.PropertyUtility;

import java.util.HashMap;

public class BookAccountTest extends Hooks {

    public String userId;
    public String token;
    public RequestAccount requestAccountBody;
    public AccountActions accountActions;
    public RequestAccountBooks requestAccountBooks;
    public BookStoreActions bookStoreActions;
    public RequestAccountBook requestAccountBook;

    @Test
    public void method() {
        System.out.println("Step 1: Create new account");
        createAccount();
        ExtentUtility.attachReportLog(ReportStep.PASS_STEP, "The user creates a new account with success");

        System.out.println("\nStep 2: Generate New Token");
        generateToken();
        ExtentUtility.attachReportLog(ReportStep.PASS_STEP, "The user generates a token for the new account");

        System.out.println("\nStep 3: Get New Account");
        getSpecificAccount();
        ExtentUtility.attachReportLog(ReportStep.PASS_STEP, "The user validates the presence of the new account");

        System.out.println("\nStep 4: Add books to account");
        addBooksToAccount();

        System.out.println("\nStep 5: Get New Account with Books");
        getSpecificAccount();
        ExtentUtility.attachReportLog(ReportStep.PASS_STEP, "The user validates the presence of the new account with books");

        System.out.println("\nStep 6: Update specific book from account");
        updateSpecificBook();
        ExtentUtility.attachReportLog(ReportStep.PASS_STEP, "The user updates a specific book");

        System.out.println("\nStep 7: Delete specific book from account");
        deleteSpecificBook();
        ExtentUtility.attachReportLog(ReportStep.PASS_STEP, "The user deletes a specific book");

        System.out.println("\nStep 8: Get New Account with Books");
        getSpecificAccount();
        ExtentUtility.attachReportLog(ReportStep.PASS_STEP, "The user validates the presence of the new account with books without the deleted one");

        System.out.println("\nStep 9: Delete account books");
        deleteAccountBooks();
        ExtentUtility.attachReportLog(ReportStep.PASS_STEP, "The user deletes all the books from the account");

        System.out.println("\nStep 10: Get New Account with Books");
        getSpecificAccount();
        ExtentUtility.attachReportLog(ReportStep.PASS_STEP, "The user validates the account has no books");

        System.out.println("\nStep 11: Delete new account");
        deleteSpecificAccount();
        ExtentUtility.attachReportLog(ReportStep.PASS_STEP, "The user deletes the new account");

        System.out.println("\nStep 12: Recheck the account");
        getSpecificAccount();
        ExtentUtility.attachReportLog(ReportStep.PASS_STEP, "The user validates the account is deleted");
    }

    public void createAccount() {
        accountActions = new AccountActions();
        propertyUtility = new PropertyUtility("requestData/createAccountData");
        requestAccountBody = new RequestAccount(propertyUtility.getAllData());
        ResponseAccountSuccess responseAccountBody = accountActions.createNewAccount(requestAccountBody);
        userId = responseAccountBody.getUserID();
    }

    public void generateToken() {
        ResponseTokenSuccess responseTokenSuccess = accountActions.generateToken(requestAccountBody);
        token = responseTokenSuccess.getToken();
    }

    public void getSpecificAccount() {
        accountActions.getSpecificAccount(token, userId, requestAccountBody);
    }

    public void updateSpecificBook() {
        propertyUtility = new PropertyUtility("requestData/booksAccountData");
        HashMap<String, String> testData = propertyUtility.getAllData();

        String expectedBook = testData.get("expectedIsbn");
        String actualBook = testData.get("actaualIsbn");

        testData.put("userId", userId);
        testData.put("isbn", expectedBook);
        requestAccountBook = new RequestAccountBook(testData);

        bookStoreActions.updateSpecificBookFromAccount(token, requestAccountBook, actualBook);
    }

    public void deleteSpecificBook() {
        bookStoreActions.deleteSpecificBookFromAccount(token, requestAccountBook);
    }

    public void deleteAccountBooks(){
        bookStoreActions.deleteBooksFromAccount(token, userId);
    }

    public void deleteSpecificAccount() {
        accountActions.deleteSpecificAccount(token, userId);
    }

    public void addBooksToAccount() {
        propertyUtility = new PropertyUtility("requestData/booksAccountData");
        HashMap<String, String> testData = propertyUtility.getAllData();
        testData.put("userId", userId);
        requestAccountBooks = new RequestAccountBooks(testData);

        bookStoreActions = new BookStoreActions();
        bookStoreActions.addBooksToAccount(token, requestAccountBooks);
    }
}
