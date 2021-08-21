package com.example.hoangcv2_gym;

import androidx.room.ColumnInfo;
import androidx.room.Entity;
import androidx.room.PrimaryKey;

@Entity(tableName = "exercise")
public class Exercise {
    @PrimaryKey(autoGenerate = true)
    private int id;
    @ColumnInfo(name = "title")
    String title;
    @ColumnInfo(name = "imageTitle")
    String imageTitle;
    @ColumnInfo(name = "category")
    String category;
    @ColumnInfo(name = "time")
    String time;
    @ColumnInfo(name = "description")
    String description;
    @ColumnInfo(name = "instruction")
    String instruction;
    @ColumnInfo(name = "imageInstruction")
    String imageInstruction;
    @ColumnInfo(name = "videoInstruction")
    String videoInstruction;
    @ColumnInfo(name = "moreDetail")
    String moreDetail;

    public Exercise(String title, String imageTitle, String category, String time, String description, String instruction, String imageInstruction, String videoInstruction, String moreDetail) {
        this.title = title;
        this.imageTitle = imageTitle;
        this.category = category;
        this.time = time;
        this.description = description;
        this.instruction = instruction;
        this.imageInstruction = imageInstruction;
        this.videoInstruction = videoInstruction;
        this.moreDetail = moreDetail;
    }

    public Exercise() {
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getImageTitle() {
        return imageTitle;
    }

    public void setImageTitle(String imageTitle) {
        this.imageTitle = imageTitle;
    }

    public String getCategory() {
        return category;
    }

    public void setCategory(String category) {
        this.category = category;
    }

    public String getTime() {
        return time;
    }

    public void setTime(String time) {
        this.time = time;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getInstruction() {
        return instruction;
    }

    public void setInstruction(String instruction) {
        this.instruction = instruction;
    }

    public String getImageInstruction() {
        return imageInstruction;
    }

    public void setImageInstruction(String imageInstruction) {
        this.imageInstruction = imageInstruction;
    }

    public String getVideoInstruction() {
        return videoInstruction;
    }

    public void setVideoInstruction(String videoInstruction) {
        this.videoInstruction = videoInstruction;
    }

    public String getMoreDetail() {
        return moreDetail;
    }

    public void setMoreDetail(String moreDetail) {
        this.moreDetail = moreDetail;
    }
}
