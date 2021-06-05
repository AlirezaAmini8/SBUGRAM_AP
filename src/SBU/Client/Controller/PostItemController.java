package SBU.Client.Controller;

import SBU.Client.Model.API;
import SBU.Client.Model.PageLoader;
import SBU.Client.Model.Post;
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
    public Label postDate;
    public ImageView Repost_button;
    public ImageView AddComment_button;
    public ImageView ViewCmnt_button;
    public ImageView Like_button;
    public Label likesNum_label;
    public Label repostsNum_label;
    public Label liked_label;
    public Label disliked_label;
    public Label reposted_label;
    Post post;

    //each list item will have its exclusive controller in runtime so we set the controller as we load the fxml
    public PostItemController(Post post) throws IOException {
        new PageLoader().load("PostItem", this);
        this.post = post;
    }

    //this anchor pane is returned to be set as the list view item
    public AnchorPane init() {
        username_label.setText(post.getName());
        title_field.setText(post.getTitle());
        profileImage.setImage(new Image(new ByteArrayInputStream(SignUpPageController.profile.getProfilePhoto())));
        postTime.setText(post.getTime().toString());
        postDate.setText(post.getDate().toString());
        PostImage.setImage(new Image(new ByteArrayInputStream(post.getPostimage())));
        Description_field.setText(post.getDescription());
        likesNum_label.setText(String.valueOf(post.getLikesnum()));
        repostsNum_label.setText(String.valueOf(post.getRepostsnum()));
        return root;
    }

    public void Repost(MouseEvent actionEvent) {
        post.setDate(java.time.LocalDate.now());
        post.setTime(java.time.LocalTime.now());
        if(API.repost(SignUpPageController.profile,post)){
            reposted_label.setVisible(true);
        }else {
            reposted_label.setVisible(false);
        }
    }

    public void AddComment(MouseEvent actionEvent) {
        //
    }

    public void ViewCmnt(MouseEvent actionEvent) {
        //
    }

    public void Like(MouseEvent actionEvent) {
        if(liked_label.isVisible()){
            if (API.likepost(SignUpPageController.profile, post, false)) {
                post.setLikesnum(post.getLikesnum() - 1);
                likesNum_label.setText(String.valueOf(post.getLikesnum()));
                liked_label.setVisible(false);
                disliked_label.setVisible(true);
            }
        }else {
            if (API.likepost(SignUpPageController.profile, post, true)) {
                post.setLikesnum(post.getLikesnum() + 1);
                likesNum_label.setText(String.valueOf(post.getLikesnum()));
                disliked_label.setVisible(false);
                liked_label.setVisible(true);
            }
        }
    }

}
