/*
 * This Java source file was generated by the Gradle 'init' task.
 */
package com.crio.xlido;

import com.crio.xlido.Commands.CommandInvoker;
import com.crio.xlido.Commands.ICommand;
import com.crio.xlido.Entities.Event;
import com.crio.xlido.Entities.Question;
import com.crio.xlido.Entities.User;
import com.crio.xlido.Services.EventService;
import com.crio.xlido.Services.QuestionService;
import com.crio.xlido.Services.UserService;
import com.crio.xlido.repositories.EventRepository;
import com.crio.xlido.repositories.QuestionRepository;
import com.crio.xlido.repositories.UserRepository;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.Arrays;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.List;

public class App {

    private UserRepository userRepository = new UserRepository();
    private UserService userService = new UserService(userRepository);
    private EventRepository eventRepository = new EventRepository();
    private EventService eventService = new EventService(eventRepository, userRepository);
    private QuestionRepository questionRepository = new QuestionRepository(userRepository);
    private QuestionService questionService = new QuestionService(questionRepository, userRepository, eventRepository);


    public static void main(String[] args) {

        // Test your code by ading commands in sample_input/sample_input_one.txt
        // Run run.sh script using "bash run.sh" in your terminal.
        if (args.length == 1){
            List<String> commandLineArgs = new LinkedList<>(Arrays.asList(args));
            String inputFile = commandLineArgs.get(0).split("=")[1];
            try {
                List<String> file_commands = Files.readAllLines(Paths.get(inputFile));
                // Execute the commands
                new App().run(file_commands);
            } catch (IOException e) {
                e.printStackTrace();
            }
            return;
        }        

        // OR
        // Test your code by adding commands in this list
        List<String> inplace_commands = new LinkedList<>(){
            {
            }
        };

        new App().run(inplace_commands);
 
    }
    public void run(List<String> commands){

        Iterator<String> it = commands.iterator();
        while(it.hasNext()){
            String line = it.next();
                if(line == null){
                    break;
                }
                List<String> tokens = Arrays.asList(line.split(","));

                try {
                    //Execute Services
                    switch(tokens.get(0)){
                        case "CREATE_USER":
                            User user = userService.createUser(tokens);  
                            System.out.println("User ID: "+user.getUserId());
                        break;
                        case "CREATE_EVENT":
                            try{
                                Event event = eventService.createEvent(tokens);
                                System.out.println("Event ID: "+event.getEventId());
                            }
                            catch(Exception e){
                                System.out.println("ERROR: "+e.getMessage());
                            }
                        break;
                        case "DELETE_EVENT":
                            try{
                                eventService.deleteEvent(tokens);
                                System.out.println("EVENT_DELETED "+tokens.get(1));
                            }
                            catch(Exception e){
                                System.out.println("ERROR: "+e.getMessage());
                            }
                        break;
                        case "ADD_QUESTION":
                            try{
                                Question question = questionService.addQuestion(tokens);
                                System.out.println("Question ID: "+question.getQuestionId());
                            }
                            catch(Exception e){
                                System.out.println("ERROR: "+e.getMessage());
                            }

                        break;
                        case "DELETE_QUESTION":
                            try{
                                questionService.deleteQuestion(tokens);
                                System.out.println("QUESTION_DELETED "+tokens.get(1));
                            }
                            catch(Exception e){
                                System.out.println("ERROR: "+e.getMessage());
                            }
                        break;
                        case "UPVOTE_QUESTION":
                            try{
                                questionService.upvoteQuestion(tokens);
                                System.out.println("QUESTION_UPVOTED "+tokens.get(1));
                            }
                            catch(Exception e){
                                System.out.println("ERROR: "+e.getMessage());
                            }
                        break;
                        case "REPLY_QUESTION":
                            try{
                                questionService.replyQuestion(tokens);
                                System.out.println("REPLY_ADDED");
                            }
                            catch(Exception e){
                                System.out.println("ERROR: "+e.getMessage());
                            }
                        break;
                        case "LIST_QUESTIONS":
                            try{
                                List<Question> questions = questionService.getQuestions(tokens);
                                for(Question question:questions){
                                    System.out.println("Question ID: "+question.getQuestionId());
                                    System.out.println("Content: "+question.getQuestionContent());
                                    System.out.println("Votes: "+question.getNoOfVotes());
                                    System.out.println("Replies:");
                                    if(question.getReply()!=null) System.out.println("  - User "+question.getReply().getUserId()+": "+question.getReply().getContent());
                                    System.out.println();
                                    }
                                }
                                catch(Exception e){
                                    System.out.println("ERROR: "+e.getMessage());
                                }
                        break;
                        default:
                            throw new RuntimeException("INVALID_COMMAND");
                }
                } catch (Exception e) {
                    System.out.println("ERROR: " + e);
                }
        }
    }

    // public void register(){

    // }
}