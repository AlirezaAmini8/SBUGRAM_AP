package Controller;

import Model.API;
import Model.ClientEXE;
import Model.PageLoader;
import Model.Post;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import java.io.*;


public class PostItemController {
    public AnchorPane root;
    public ImageView profileImage;
    public Label username_label;
    public TextField title_field;
    public TextField Description_field;
    public ImageView PostImage;
    public Label postTime;
    public ImageView Repost_button;
    public ImageView AddComment_button;
    public ImageView ViewCmnt_button;
    public ImageView Like_button;
    public Label likesNum_label;
    public Label repostsNum_label;
    public Label writer_label;
    Post post;

    //each list item will have its exclusive controller in runtime so we set the controller as we load the fxml
    public PostItemController(Post post) throws IOException {
        new PageLoader().load("PostItem", this);
        this.post = post;
    }

    //this anchor pane is returned to be set as the list view item
    public AnchorPane init() {
        writer_label.setText(post.getWriter());
        username_label.setText(post.getUsername());
        title_field.setText(post.getTitle());
        profileImage.setImage(new Image(new ByteArrayInputStream(ClientEXE.profile.getProfilePhoto())));
        postTime.setText(post.getTime());
        PostImage.setImage(new Image(new ByteArrayInputStream(post.getPostimage())));
        Description_field.setText(post.getDescription());
        likesNum_label.setText(String.valueOf(post.getLikesnum()));
        repostsNum_label.setText(String.valueOf(post.getRepostsnum()));
        return root;
    }

    public synchronized void Repost(MouseEvent actionEvent) {
        API.repost(ClientEXE.profile,post);
        PageLoader.showalert("SBU GRAM","You reposted this post.",null);
    }

    public void AddComment(MouseEvent actionEvent) throws IOException {
        ClientEXE.toCommentPost=post;
        new PageLoader().load("AddComment");
    }

    public void ViewCmnt(MouseEvent actionEvent) throws IOException {
        ClientEXE.toCommentPost=post;
        new PageLoader().load("ViewComments");
    }

    public synchronized void Like(MouseEvent actionEvent) {
        Post lastpost=post;
        if(ClientEXE.profile.likepost.contains(post)){
            Boolean islike=API.likepost(ClientEXE.profile, post, false);
            if (!islike) {
                ClientEXE.profile.likepost.remove(post);
                post.setLikesnum(post.getLikesnum() - 1);
                likesNum_label.setText(String.valueOf(post.getLikesnum()));
            }
        }else {
            Boolean islike=API.likepost(ClientEXE.profile, post, true);
            if (islike) {
                ClientEXE.profile.likepost.add(post);
                post.setLikesnum(post.getLikesnum() + 1);
                likesNum_label.setText(String.valueOf(post.getLikesnum()));
            }
        }
        API.updatepost(ClientEXE.profile,lastpost,post);
    }

}
