package com.peregudova.multinote.requests;

import com.google.gson.Gson;
import org.apache.http.NameValuePair;
import org.apache.http.client.fluent.Content;
import org.apache.http.client.fluent.Request;
import org.apache.http.message.BasicNameValuePair;

import java.io.IOException;
import java.nio.charset.Charset;
import java.util.ArrayList;
import java.util.Collection;

public class Requester {
    private final String url = "http://mail.mioks.ru/notes/";

    public Requester(){}

    public LoginAnswer loginUser(User user) throws IOException {
        final Collection<NameValuePair> params = new ArrayList();
        params.add(new BasicNameValuePair("login", user.getLogin()));
        params.add(new BasicNameValuePair("password", user.getPassword()));
        //ошибка
        final Content postResultForm = Request.Post(url+"login/index.php")
                .bodyForm(params, Charset.defaultCharset())
                .execute().returnContent();
        return new Gson().fromJson(postResultForm.asString(), LoginAnswer.class);
    }

    public RegisterAnswer registerUser(User user) throws IOException {
        final Collection<NameValuePair> params = new ArrayList();
        params.add(new BasicNameValuePair("reg_login", user.getLogin()));
        params.add(new BasicNameValuePair("reg_password", user.getPassword()));
        params.add(new BasicNameValuePair("reg_email", user.getEmail()));

        final Content postResultForm = Request.Post(url+"login/register.php")
                .bodyForm(params, Charset.defaultCharset())
                .execute().returnContent();
        return new Gson().fromJson(postResultForm.asString(), RegisterAnswer.class);
    }

    public NotesAnswer getallnotes(GetAllNotesCommand command) throws IOException {
        final Collection<NameValuePair> params = new ArrayList();
        params.add(new BasicNameValuePair("command", command.getCommand()));
        params.add(new BasicNameValuePair("user", command.getUser()));
        params.add(new BasicNameValuePair("token", command.getToken()));

        final Content postResultForm = Request.Post(url+"app/getnotes.php")
                .bodyForm(params, Charset.defaultCharset())
                .execute().returnContent();
        return new Gson().fromJson(postResultForm.asString(), NotesAnswer.class);
    }

    public NoteAnswer getnote(GetNoteCommand getNoteCommand) throws IOException {
        final Collection<NameValuePair> params = new ArrayList();
        params.add(new BasicNameValuePair("command", getNoteCommand.getCommand()));
        params.add(new BasicNameValuePair("user", getNoteCommand.getUser()));
        params.add(new BasicNameValuePair("token", getNoteCommand.getToken()));
        params.add(new BasicNameValuePair("id_note", getNoteCommand.getId_note()));

        final Content postResultForm = Request.Post(url+"app/getnotes.php")
                .bodyForm(params, Charset.defaultCharset())
                .execute().returnContent();
        return new Gson().fromJson(postResultForm.asString(), NoteAnswer.class);
    }

    public ChangeAnswer changenote(ChangeNoteCommand getNoteCommand) throws IOException {
        final Collection<NameValuePair> params = new ArrayList();
        params.add(new BasicNameValuePair("command", getNoteCommand.getCommand()));
        params.add(new BasicNameValuePair("user", getNoteCommand.getUser()));
        params.add(new BasicNameValuePair("token", getNoteCommand.getToken()));
        params.add(new BasicNameValuePair("id_note", getNoteCommand.getId_note()));

        final Content postResultForm = Request.Post(url+"app/getnotes.php")
                .bodyForm(params, Charset.defaultCharset())
                .execute().returnContent();
        return new Gson().fromJson(postResultForm.asString(), ChangeAnswer.class);
    }

    public SaveAnswer saveNote(SaveNoteCommand saveNoteCommand) throws IOException {
        final Collection<NameValuePair> params = new ArrayList();
        params.add(new BasicNameValuePair("command", saveNoteCommand.getCommand()));
        params.add(new BasicNameValuePair("user", saveNoteCommand.getUser()));
        params.add(new BasicNameValuePair("token", saveNoteCommand.getToken()));
        params.add(new BasicNameValuePair("id_note", saveNoteCommand.getId_note()));
        params.add(new BasicNameValuePair("text", saveNoteCommand.getText()));

        final Content postResultForm = Request.Post(url+"app/getnotes.php")
                .bodyForm(params, Charset.defaultCharset())
                .execute().returnContent();
        return new Gson().fromJson(postResultForm.asString(), SaveAnswer.class);
    }

    public NewAnswer newNote(NewNoteCommand newNoteCommand) throws IOException {
        final Collection<NameValuePair> params = new ArrayList();
        params.add(new BasicNameValuePair("command", newNoteCommand.getCommand()));
        params.add(new BasicNameValuePair("user", newNoteCommand.getUser()));
        params.add(new BasicNameValuePair("token", newNoteCommand.getToken()));


        final Content postResultForm = Request.Post(url+"app/getnotes.php")
                .bodyForm(params, Charset.defaultCharset())
                .execute().returnContent();
        return new Gson().fromJson(postResultForm.asString(), NewAnswer.class);
    }

    public ExitAnswer exitAnswer(ExitCommand exitCommand) throws IOException {
        final Collection<NameValuePair> params = new ArrayList();
        params.add(new BasicNameValuePair("command", exitCommand.getCommand()));
        params.add(new BasicNameValuePair("user", exitCommand.getUser()));
        params.add(new BasicNameValuePair("token", exitCommand.getToken()));


        final Content postResultForm = Request.Post(url+"app/getnotes.php")
                .bodyForm(params, Charset.defaultCharset())
                .execute().returnContent();
        return new Gson().fromJson(postResultForm.asString(), ExitAnswer.class);
    }

    public ListAccessAnswer listAccess(ListAccessCommand command) throws IOException {
        final Collection<NameValuePair> params = new ArrayList();
        params.add(new BasicNameValuePair("command", command.getCommand()));
        params.add(new BasicNameValuePair("user", command.getUser()));
        params.add(new BasicNameValuePair("token", command.getToken()));
        params.add(new BasicNameValuePair("id_note", command.getId_note()));



        final Content postResultForm = Request.Post(url+"app/getnotes.php")
                .bodyForm(params, Charset.defaultCharset())
                .execute().returnContent();
        //System.out.println(postResultForm.asString());
        return new Gson().fromJson(postResultForm.asString(), ListAccessAnswer.class);
    }

    public AddAccessAnswer addAccess(AddAccessCommand command) throws IOException {
        final Collection<NameValuePair> params = new ArrayList();
        params.add(new BasicNameValuePair("command", command.getCommand()));
        params.add(new BasicNameValuePair("user", command.getUser()));
        params.add(new BasicNameValuePair("token", command.getToken()));
        params.add(new BasicNameValuePair("id_note", command.getId_note()));
        params.add(new BasicNameValuePair("login", command.getLogin()));



        final Content postResultForm = Request.Post(url+"app/getnotes.php")
                .bodyForm(params, Charset.defaultCharset())
                .execute().returnContent();
        //System.out.println(postResultForm.asString());
        return new Gson().fromJson(postResultForm.asString(), AddAccessAnswer.class);
    }
}
