package by.epamtc.task04.server.entity;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

public class TextComposite extends TextComponent implements Serializable {

    private static final long serialVersionUID = -5995679943949074322L;

    private List<TextComponent> children;

    public TextComposite(TextComponentType type) {
        super(type);
        children = new ArrayList<>();
    }

    @Override
    public String text() {
        StringBuilder builder = new StringBuilder();

        for (TextComponent child : children) {
            builder.append(child.text());
        }

        return builder.toString();
    }

    public void addChildren(TextComponent component) {
        children.add(component);
    }

    public void removeChildren(TextComponent component) {
        children.remove(component);
    }

    public List<TextComponent> getChildren() {
        return children;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        if (!super.equals(o)) return false;
        TextComposite that = (TextComposite) o;
        return Objects.equals(children, that.children);
    }

    @Override
    public int hashCode() {
        return Objects.hash(super.hashCode(), children);
    }

    @Override
    public String toString() {
        return "TextComposite{" +
                "children=" + children +
                '}';
    }
}
