package educationalproject.programmingstuff.test_data_prep;

import lombok.AllArgsConstructor;

import java.util.List;

@AllArgsConstructor
public class ListBuilder<T> { // TODO: 17.04.2022 delete this shit out
    List<T> list;

    public ListBuilder<T> list(List<T> list){
        this.list=list;
        return this;
    }

    public List<T> build() {
        return list;
    }
}
