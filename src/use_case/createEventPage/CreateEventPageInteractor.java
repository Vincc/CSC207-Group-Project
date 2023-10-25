package use_case.createEventPage;




public class CreateEventPageInteractor implements CreateEventPageInputBoundary {
    final CreateEventPageOutputBoundery createEventPagePresenter;
    public CreateEventPageInteractor(CreateEventPageOutputBoundery createEventPageOutputBoundery){
        this.createEventPagePresenter =  createEventPageOutputBoundery;
    }

    @Override
    public void execute() {
        createEventPagePresenter.prepareSuccessView();
    }
}
