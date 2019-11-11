package ch.amk.exercise1.ui;

import java.util.ArrayList;
import java.util.List;

import ch.amk.exercise1.ItemFragment;
import ch.amk.exercise1.models.Post;

public class PostContent {
    public static final List<Post> ITEMS = new ArrayList<>();

    public static void addItem(Post post) {
        ITEMS.add(0, post);
    }
}
