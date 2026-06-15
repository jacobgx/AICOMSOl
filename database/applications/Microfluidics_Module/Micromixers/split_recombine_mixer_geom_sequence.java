/*
 * split_recombine_mixer_geom_sequence.java
 */

import com.comsol.model.*;
import com.comsol.model.util.*;

/** Model exported on Apr 30 2026, 11:10 by COMSOL 6.3.0.290. */
public class split_recombine_mixer_geom_sequence {

  public static Model run() {
    Model model = ModelUtil.create("Model");

    model
         .modelPath("D:\\Program Files\\COMSOL\\COMSOL63\\Multiphysics\\applications\\Microfluidics_Module\\Micromixers");

    model.component().create("comp1", true);

    model.component("comp1").geom().create("geom1", 3);
    model.component("comp1").geom("geom1").geomRep("comsol");

    model.component("comp1").mesh().create("mesh1");
    model.component("comp1").mesh("mesh1").contribute("geom/detail", true);

    model.component("comp1").geom("geom1").lengthUnit("mm");
    model.component("comp1").geom("geom1").create("blk1", "Block");
    model.component("comp1").geom("geom1").feature("blk1").set("size", new double[]{0.5, 2, 0.5});
    model.component("comp1").geom("geom1").feature().duplicate("blk2", "blk1");
    model.component("comp1").geom("geom1").feature("blk2").set("pos", new int[]{1, 0, 0});
    model.component("comp1").geom("geom1").feature().duplicate("blk3", "blk2");
    model.component("comp1").geom("geom1").feature("blk3").set("pos", new double[]{0.5, -1.5, 0});
    model.component("comp1").geom("geom1").feature("blk3").setIndex("layer", 1, 0);
    model.component("comp1").geom("geom1").feature("blk3").set("layerbottom", false);
    model.component("comp1").geom("geom1").feature("blk3").set("layerfront", true);
    model.component("comp1").geom("geom1").feature().duplicate("blk4", "blk3");
    model.component("comp1").geom("geom1").feature("blk4").set("size", new double[]{2, 0.5, 0.5});
    model.component("comp1").geom("geom1").feature("blk4").set("pos", new double[]{0.5, -1.5, 0.5});
    model.component("comp1").geom("geom1").feature("blk4").set("layerfront", false);
    model.component("comp1").geom("geom1").feature("blk4").set("layerleft", true);
    model.component("comp1").geom("geom1").feature().duplicate("blk5", "blk4");
    model.component("comp1").geom("geom1").feature("blk5").set("size", new double[]{0.5, 1.5, 0.5});
    model.component("comp1").geom("geom1").feature("blk5").set("pos", new double[]{2, -3, 0.5});
    model.component("comp1").geom("geom1").feature("blk5").set("layerleft", false);
    model.component("comp1").geom("geom1").feature().duplicate("blk6", "blk5");
    model.component("comp1").geom("geom1").feature("blk6").set("pos", new double[]{1.5, -4, 0.5});
    model.component("comp1").geom("geom1").feature().duplicate("blk7", "blk6");
    model.component("comp1").geom("geom1").feature("blk7").set("size", new double[]{0.5, 2.5, 0.5});
    model.component("comp1").geom("geom1").feature("blk7").set("pos", new double[]{2.5, -5, 0.5});
    model.component("comp1").geom("geom1").feature().duplicate("blk8", "blk7");
    model.component("comp1").geom("geom1").feature("blk8").set("size", new double[]{2, 0.5, 0.5});
    model.component("comp1").geom("geom1").feature("blk8").set("pos", new int[]{0, -4, 0});
    model.component("comp1").geom("geom1").feature().duplicate("blk9", "blk8");
    model.component("comp1").geom("geom1").feature("blk9").set("pos", new int[]{1, -5, 0});
    model.component("comp1").geom("geom1").feature("blk9").set("layerleft", true);
    model.component("comp1").geom("geom1").feature().duplicate("blk10", "blk9");
    model.component("comp1").geom("geom1").feature("blk10").set("size", new double[]{1.5, 0.5, 0.5});
    model.component("comp1").geom("geom1").feature("blk10").set("pos", new double[]{0, -5.5, 0});
    model.component("comp1").geom("geom1").feature("blk10").set("layerleft", false);
    model.component("comp1").geom("geom1").feature().duplicate("blk11", "blk10");
    model.component("comp1").geom("geom1").feature("blk11").set("size", new double[]{0.5, 1, 0.5});
    model.component("comp1").geom("geom1").feature("blk11").set("pos", new int[]{0, -5, 0});
    model.component("comp1").geom("geom1").feature().duplicate("blk12", "blk11");
    model.component("comp1").geom("geom1").feature("blk12").set("pos", new double[]{0.5, -6.5, 0});
    model.component("comp1").geom("geom1").feature("blk12").setIndex("layer", 0.5, 0);
    model.component("comp1").geom("geom1").feature("blk12").set("layerfront", true);
    model.component("comp1").geom("geom1").run("blk12");
    model.component("comp1").geom("geom1").create("pard1", "PartitionDomains");
    model.component("comp1").geom("geom1").feature("pard1").selection("domain")
         .set(new String[]{"blk1", "blk9", "blk2", "blk10", "blk3", "blk11", "blk4", "blk12", "blk5", "blk6", 
         "blk7", "blk8"}, new int[][]{{1}, {1, 2}, {1}, {1}, {1, 2}, {1}, {1, 2}, {1, 2}, {1}, {1}, {1}, {1}});
    model.component("comp1").geom("geom1").feature("pard1").set("partitionwith", "extendedfaces");
    model.component("comp1").geom("geom1").feature("pard1").selection("extendedface")
         .set(new String[]{"blk1", "blk9", "blk2", "blk10", "blk3", "blk11", "blk4", "blk12", "blk5", "blk6", 
         "blk7", "blk8"}, new int[][]{{1, 2, 3, 4, 5, 6}, {1, 2, 3, 4, 5, 6, 7, 8, 9, 10, 11}, {1, 2, 3, 4, 5, 6}, {1, 2, 3, 4, 5, 6}, {1, 2, 3, 4, 5, 6, 7, 8, 9, 10, 11}, {1, 2, 3, 4, 5, 6}, {1, 2, 3, 4, 5, 6, 7, 8, 9, 10, 11}, {1, 2, 3, 4, 5, 6, 7, 8, 9, 10, 11}, {1, 2, 3, 4, 5, 6}, {1, 2, 3, 4, 5, 6}, {1, 2, 3, 4, 5, 6}, {1, 2, 3, 4, 5, 6}});
    model.component("comp1").geom("geom1").run("fin");
    model.component("comp1").geom("geom1").create("sel1", "ExplicitSelection");
    model.component("comp1").geom("geom1").feature("sel1").selection("selection").init();
    model.component("comp1").geom("geom1").feature("sel1").label("\u51e0\u4f55");
    model.component("comp1").geom("geom1").feature("sel1").selection("selection").set("fin");
    model.component("comp1").geom("geom1").run("sel1");
    model.component("comp1").geom("geom1").create("sel2", "ExplicitSelection");
    model.component("comp1").geom("geom1").feature("sel2").label("\u6d41\u5165");
    model.component("comp1").geom("geom1").feature("sel2").selection("selection").init(2);
    model.component("comp1").geom("geom1").feature("sel2").label("\u6d41\u5165 1");
    model.component("comp1").geom("geom1").feature("sel2").selection("selection").set("fin", 101);
    model.component("comp1").geom("geom1").run("sel2");
    model.component("comp1").geom("geom1").create("sel3", "ExplicitSelection");
    model.component("comp1").geom("geom1").feature("sel3").label("\u6d41\u5165 2");
    model.component("comp1").geom("geom1").feature("sel3").selection("selection").init(2);
    model.component("comp1").geom("geom1").feature("sel3").selection("selection").set("fin", 26);
    model.component("comp1").geom("geom1").run("sel3");
    model.component("comp1").geom("geom1").create("sel4", "ExplicitSelection");
    model.component("comp1").geom("geom1").feature("sel4").label("\u51fa\u53e3");
    model.component("comp1").geom("geom1").feature("sel4").selection("selection").init(2);
    model.component("comp1").geom("geom1").feature("sel4").selection("selection").set("fin", 28);
    model.component("comp1").geom("geom1").run("sel4");
    model.component("comp1").geom("geom1").create("unisel1", "UnionSelection");
    model.component("comp1").geom("geom1").feature("unisel1").label("\u5165\u53e3");
    model.component("comp1").geom("geom1").feature("unisel1").set("entitydim", 2);
    model.component("comp1").geom("geom1").feature("unisel1").set("input", new String[]{"sel2", "sel3"});
    model.component("comp1").geom("geom1").run("unisel1");
    model.component("comp1").geom("geom1").create("sel5", "ExplicitSelection");
    model.component("comp1").geom("geom1").feature("sel5").label("A-A");
    model.component("comp1").geom("geom1").feature("sel5").selection("selection").init(2);
    model.component("comp1").geom("geom1").feature("sel5").selection("selection").set("fin", 60);
    model.component("comp1").geom("geom1").run("sel5");
    model.component("comp1").geom("geom1").create("sel6", "ExplicitSelection");
    model.component("comp1").geom("geom1").feature("sel6").label("B-B");
    model.component("comp1").geom("geom1").feature("sel6").selection("selection").init(2);
    model.component("comp1").geom("geom1").feature("sel6").selection("selection").set("fin", 125);
    model.component("comp1").geom("geom1").run("sel6");
    model.component("comp1").geom("geom1").create("sel7", "ExplicitSelection");
    model.component("comp1").geom("geom1").feature("sel7").label("C-C");
    model.component("comp1").geom("geom1").feature("sel7").selection("selection").init(2);
    model.component("comp1").geom("geom1").feature("sel7").selection("selection").set("fin", 132);
    model.component("comp1").geom("geom1").run("sel7");
    model.component("comp1").geom("geom1").create("sel8", "ExplicitSelection");
    model.component("comp1").geom("geom1").feature("sel8").label("D-D");
    model.component("comp1").geom("geom1").feature("sel8").selection("selection").init(2);
    model.component("comp1").geom("geom1").feature("sel8").selection("selection").set("fin", 32);
    model.component("comp1").geom("geom1").run("sel8");
    model.component("comp1").geom("geom1").create("adjsel1", "AdjacentSelection");
    model.component("comp1").geom("geom1").feature("adjsel1").label("\u58c1 - \u5916\u90e8");
    model.component("comp1").geom("geom1").feature("adjsel1").set("input", new String[]{"sel1"});
    model.component("comp1").geom("geom1").run("adjsel1");
    model.component("comp1").geom("geom1").create("adjsel2", "AdjacentSelection");
    model.component("comp1").geom("geom1").feature("adjsel2").label("\u58c1 - \u5185\u90e8");
    model.component("comp1").geom("geom1").feature("adjsel2").set("input", new String[]{"sel1"});
    model.component("comp1").geom("geom1").feature("adjsel2").set("exterior", false);
    model.component("comp1").geom("geom1").feature("adjsel2").set("interior", true);
    model.component("comp1").geom("geom1").run("adjsel2");
    model.component("comp1").geom("geom1").create("difsel1", "DifferenceSelection");
    model.component("comp1").geom("geom1").feature("difsel1").label("\u7f51\u683c\u63a7\u5236");
    model.component("comp1").geom("geom1").feature("difsel1").set("entitydim", 2);
    model.component("comp1").geom("geom1").feature("difsel1").set("add", new String[]{"adjsel2"});
    model.component("comp1").geom("geom1").feature("difsel1")
         .set("subtract", new String[]{"sel5", "sel6", "sel7", "sel8"});
    model.component("comp1").geom("geom1").run("difsel1");
    model.component("comp1").geom("geom1").create("mcf1", "MeshControlFaces");
    model.component("comp1").geom("geom1").feature("mcf1").selection("input").named("difsel1");
    model.component("comp1").geom("geom1").run("mcf1");
    model.component("comp1").geom("geom1").create("difsel2", "DifferenceSelection");
    model.component("comp1").geom("geom1").feature("difsel2").label("\u5916\u58c1");
    model.component("comp1").geom("geom1").feature("difsel2").set("entitydim", 2);
    model.component("comp1").geom("geom1").feature("difsel2").set("add", new String[]{"adjsel1"});
    model.component("comp1").geom("geom1").feature("difsel2").set("subtract", new String[]{"sel4", "unisel1"});

    model.title(null);

    model.description("");

    model.label("split_recombine_mixer_geom_sequence.mph");

    return model;
  }

  public static void main(String[] args) {
    run();
  }

}
