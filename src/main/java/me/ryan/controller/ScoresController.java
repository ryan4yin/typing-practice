package me.ryan.controller;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import me.ryan.model.ScoreProperties;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;

@Controller
public class ScoresController {
    private static final Logger logger = LoggerFactory.getLogger(ScoresController.class);

    // 成绩列表
    @FXML
    private TableView<ScoreProperties> scoresTable;

    // 以下为列表的所有列
    // 泛型参数<模型类型，本列的类型>

    // 速度
    @FXML
    private TableColumn<ScoreProperties, String> cpm;

    // 击键
    @FXML
    private TableColumn<ScoreProperties, String> kps;

    // 码长
    @FXML
    private TableColumn<ScoreProperties, String> keysEachChar;

    // 用时
    @FXML
    private TableColumn<ScoreProperties, String> timeInterval;

    // 回车
    @FXML
    private TableColumn<ScoreProperties, String> keyEnterCount;

    // 错字
    @FXML
    private TableColumn<ScoreProperties, String> typos;

    // 字数
    @FXML
    private TableColumn<ScoreProperties, String> charactersCount;

    // 打词
    @FXML
    private TableColumn<ScoreProperties, String> ratioOfWords;

    // 退格
    @FXML
    private TableColumn<ScoreProperties, String> backspaceCount;

    // 键数
    @FXML
    private TableColumn<ScoreProperties, String> keystrokes;

    // 键准
    @FXML
    private TableColumn<ScoreProperties, String> keysAccuracy;

    // 重打
    @FXML
    private TableColumn<ScoreProperties, String> retypeCount;

    // 成绩列表
    private ObservableList<ScoreProperties> scoresList = FXCollections.observableArrayList();

    @Autowired
    public ScoresController(ScoreProperties scoreNow) {
        scoresList.add(scoreNow);
    }

    /**
     * Initializes the controller class. This method is automatically called
     * after the fxml file has been loaded.
     */
    @FXML
    private void initialize() {
        // 在这个方法被调用的时候，才能访问到 fxml 文件里定义的属性。
        this.scoresTable.setItems(scoresList);

        cpm.setCellValueFactory(new PropertyValueFactory<>("cpm"));
        kps.setCellValueFactory(new PropertyValueFactory<>("kps"));
        keysEachChar.setCellValueFactory(new PropertyValueFactory<>("keysEachChar"));
        timeInterval.setCellValueFactory(new PropertyValueFactory<>("timeInterval"));
        keyEnterCount.setCellValueFactory(new PropertyValueFactory<>("keyEnterCount"));
        typos.setCellValueFactory(new PropertyValueFactory<>("typos"));
        charactersCount.setCellValueFactory(new PropertyValueFactory<>("charactersCount"));
        ratioOfWords.setCellValueFactory(new PropertyValueFactory<>("ratioOfWords"));
        backspaceCount.setCellValueFactory(new PropertyValueFactory<>("backspaceCount"));
        keystrokes.setCellValueFactory(new PropertyValueFactory<>("keystrokes"));
        keysAccuracy.setCellValueFactory(new PropertyValueFactory<>("keysAccuracy"));
        retypeCount.setCellValueFactory(new PropertyValueFactory<>("retypeCount"));
    }

    /**
     * 跟打结束调用这个方法
     * 描述：将成绩复制一分，作为历史成绩
     */
    public void updateScores() {
        try {
            ScoreProperties scoreLast = scoresList.get(0).clone();
            scoresList.add(scoreLast);
        } catch (CloneNotSupportedException e) {
            logger.error("Copy ScoreUpdater 对象 失败。历史记录未添加");
        }
    }
}
