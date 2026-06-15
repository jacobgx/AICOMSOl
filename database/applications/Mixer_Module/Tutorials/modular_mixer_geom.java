/*
 * modular_mixer_geom.java
 */

import com.comsol.model.*;
import com.comsol.model.util.*;

/** Model exported on Apr 30 2026, 11:14 by COMSOL 6.3.0.290. */
public class modular_mixer_geom {

  public static Model run() {
    Model model = ModelUtil.create("Model");

    model.modelPath("D:\\Program Files\\COMSOL\\COMSOL63\\Multiphysics\\applications\\Mixer_Module\\Tutorials");

    model.component().create("comp1", true);

    model.component("comp1").geom().create("geom1", 3);
    model.component("comp1").geom("geom1").geomRep("comsol");

    model.component("comp1").mesh().create("mesh1");
    model.component("comp1").mesh("mesh1").contribute("geom/detail", true);

    model.param().set("H", "0.0805[m]");
    model.param().descr("H", "\u5bb9\u5668\u9ad8\u5ea6");
    model.param().set("T", "H");
    model.param().descr("T", "\u5bb9\u5668\u76f4\u5f84");
    model.param().set("C", "1/3*H");
    model.param().descr("C", "\u95f4\u9699");
    model.param().set("B", "4");
    model.param().descr("B", "\u6321\u677f\u6570");
    model.param().set("bw", "T/10");
    model.param().descr("bw", "\u6321\u677f\u5bbd\u5ea6");
    model.param().set("Da", "1/3*T");
    model.param().descr("Da", "\u53f6\u8f6e\u76f4\u5f84");
    model.param().set("shaft_diameter", "1/10*Da");
    model.param().descr("shaft_diameter", "\u8f74\u5f84");
    model.param().set("blade_length", "Da/4");
    model.param().descr("blade_length", "Rushton \u6da1\u8f6e\u53f6\u7247\u957f\u5ea6");
    model.param().set("blade_width", "Da/5");
    model.param().descr("blade_width", "\u53f6\u8f6e\u53f6\u7247\u5bbd\u5ea6");

    model.geom()
         .load(new String[]{"part1"}, "Mixer_Module\\Impellers,_Radial\\rushton_impeller.mph", new String[]{"part1"});
    model.component("comp1").geom("geom1").create("pi1", "PartInstance");
    model.component("comp1").geom("geom1").feature("pi1").set("selkeepnoncontr", false);
    model.component("comp1").geom("geom1").feature("pi1").set("part", "part1");
    model.component("comp1").geom("geom1").feature("pi1").setEntry("inputexpr", "d_hu", "shaft_diameter+Da/20");
    model.component("comp1").geom("geom1").feature("pi1").setEntry("inputexpr", "l_ib", "blade_length");
    model.component("comp1").geom("geom1").feature("pi1").setEntry("inputexpr", "w_ib", "blade_width");
    model.component("comp1").geom("geom1").feature("pi1").setEntry("inputexpr", "d_id", "Da-2*(blade_length*3/4)");
    model.component("comp1").geom("geom1").feature("pi1").setEntry("inputexpr", "d_im", "Da");
    model.component("comp1").geom("geom1").feature("pi1").setEntry("inputexpr", "hp_im", "-blade_width/2");
    model.component("comp1").geom("geom1").feature("pi1").setEntry("inputexpr", "d_is", "shaft_diameter");
    model.component("comp1").geom("geom1").selection().create("csel1", "CumulativeSelection");
    model.component("comp1").geom("geom1").selection("csel1").label("\u53f6\u8f6e\u57df");
    model.component("comp1").geom("geom1").feature("pi1").setEntry("selcontributetoobj", "pi1_csel4", "csel1");
    model.component("comp1").geom("geom1").selection().create("csel2", "CumulativeSelection");
    model.component("comp1").geom("geom1").selection("csel2").label("\u65cb\u8f6c\u5185\u58c1");
    model.component("comp1").geom("geom1").selection().create("csel3", "CumulativeSelection");
    model.component("comp1").geom("geom1").selection("csel3").label("\u65cb\u8f6c\u58c1");
    model.component("comp1").geom("geom1").feature("pi1").setEntry("selcontributetobnd", "pi1_csel1.bnd", "csel2");
    model.component("comp1").geom("geom1").feature("pi1").setEntry("selcontributetobnd", "pi1_csel2.bnd", "csel2");
    model.component("comp1").geom("geom1").feature("pi1").setEntry("selcontributetobnd", "pi1_csel3.bnd", "csel3");
    model.component("comp1").geom("geom1").selection().create("csel4", "CumulativeSelection");
    model.component("comp1").geom("geom1").selection("csel4").label("\u79fb\u9664\u8fb9");
    model.component("comp1").geom("geom1").feature("pi1").setEntry("selcontributetoedg", "pi1_sel1", "csel4");
    model.component("comp1").geom("geom1").feature("pi1").set("selkeepnoncontr", true);
    model.geom().load(new String[]{"part2"}, "Mixer_Module\\Shafts\\impeller_shaft.mph", new String[]{"part1"});
    model.component("comp1").geom("geom1").run("pi1");
    model.component("comp1").geom("geom1").create("pi2", "PartInstance");
    model.component("comp1").geom("geom1").feature("pi2").set("selkeepnoncontr", false);
    model.component("comp1").geom("geom1").feature("pi2").set("part", "part2");
    model.component("comp1").geom("geom1").feature("pi2").setEntry("inputexpr", "hp_im", "-blade_width/2");
    model.component("comp1").geom("geom1").feature("pi2").setEntry("inputexpr", "d_is", "shaft_diameter");
    model.component("comp1").geom("geom1").feature("pi2").setEntry("inputexpr", "l_is", "H-C+blade_width");
    model.component("comp1").geom("geom1").feature("pi2").setEntry("selcontributetoobj", "pi2_csel1", "csel1");
    model.component("comp1").geom("geom1").feature("pi2").setEntry("selcontributetobnd", "pi2_csel1.bnd", "csel3");
    model.component("comp1").geom("geom1").feature("pi2").setEntry("selcontributetoedg", "pi2_sel1", "csel4");
    model.component("comp1").geom("geom1").feature("pi2").set("selkeepnoncontr", true);
    model.component("comp1").geom("geom1").run("pi2");
    model.component("comp1").geom("geom1").create("uni1", "Union");
    model.component("comp1").geom("geom1").feature("uni1").selection("input").set("pi1", "pi2");
    model.component("comp1").geom("geom1").feature("uni1").set("repairtoltype", "relative");
    model.geom().load(new String[]{"part3"}, "Mixer_Module\\Tanks\\flat_bottom_tank.mph", new String[]{"part1"});
    model.component("comp1").geom("geom1").run("uni1");
    model.component("comp1").geom("geom1").create("pi3", "PartInstance");
    model.component("comp1").geom("geom1").feature("pi3").set("selkeepnoncontr", false);
    model.component("comp1").geom("geom1").feature("pi3").set("part", "part3");
    model.component("comp1").geom("geom1").feature("pi3").set("rot", 90);
    model.component("comp1").geom("geom1").feature("pi3").setEntry("inputexpr", "n_ba", "B");
    model.component("comp1").geom("geom1").feature("pi3").setEntry("inputexpr", "w_ba", "bw");
    model.component("comp1").geom("geom1").feature("pi3").setEntry("inputexpr", "d_im", "Da");
    model.component("comp1").geom("geom1").feature("pi3").setEntry("inputexpr", "d_ta", "T");
    model.component("comp1").geom("geom1").feature("pi3").setEntry("inputexpr", "h_ta", "H");
    model.component("comp1").geom("geom1").feature("pi3").setEntry("inputexpr", "hp_ta", "-C");
    model.component("comp1").geom("geom1").feature("pi3").setEntry("inputexpr", "rf_ta", 0);
    model.component("comp1").geom("geom1").selection().create("csel5", "CumulativeSelection");
    model.component("comp1").geom("geom1").selection("csel5").label("\u5bf9\u79f0");
    model.component("comp1").geom("geom1").selection().create("csel6", "CumulativeSelection");
    model.component("comp1").geom("geom1").selection("csel6").label("\u5185\u58c1");
    model.component("comp1").geom("geom1").selection().create("csel7", "CumulativeSelection");
    model.component("comp1").geom("geom1").selection("csel7").label("\u89c6\u56fe\u6291\u5236");
    model.component("comp1").geom("geom1").feature("pi3").setEntry("selcontributetobnd", "pi3_csel1.bnd", "csel5");
    model.component("comp1").geom("geom1").feature("pi3").setEntry("selcontributetobnd", "pi3_csel4.bnd", "csel6");
    model.component("comp1").geom("geom1").feature("pi3").setEntry("selcontributetobnd", "pi3_csel7.bnd", "csel7");
    model.component("comp1").geom("geom1").feature("pi3").set("selkeepnoncontr", true);
    model.component("comp1").geom("geom1").run("pi3");
    model.component("comp1").geom("geom1").create("dif1", "Difference");
    model.component("comp1").geom("geom1").feature("dif1").label("\u6d41\u4f53\u57df");
    model.component("comp1").geom("geom1").feature("dif1").selection("input").named("pi3_csel5");
    model.component("comp1").geom("geom1").feature("dif1").selection("input2").named("csel1");
    model.component("comp1").geom("geom1").feature("dif1").set("repairtoltype", "relative");
    model.component("comp1").geom("geom1").feature("dif1").set("selresult", true);
    model.component("comp1").geom("geom1").feature("dif1").set("selresultshow", "all");
    model.component("comp1").geom("geom1").run("dif1");
    model.component("comp1").geom("geom1").create("sel1", "ExplicitSelection");
    model.component("comp1").geom("geom1").feature("sel1").label("\u5e73\u538b\u70b9");
    model.component("comp1").geom("geom1").feature("sel1").selection("selection").init(0);
    model.component("comp1").geom("geom1").feature("sel1").selection("selection").set("dif1", 34);
    model.component("comp1").geom("geom1").selection().create("csel8", "CumulativeSelection");
    model.component("comp1").geom("geom1").selection("csel8").label("\u538b\u529b\u70b9\u7ea6\u675f");
    model.component("comp1").geom("geom1").feature("sel1").set("contributeto", "csel8");
    model.component("comp1").geom("geom1").run("sel1");
    model.component("comp1").geom("geom1").create("sel2", "ExplicitSelection");
    model.component("comp1").geom("geom1").feature("sel2").label("\u8981\u79fb\u9664\u7684\u8fb9");
    model.component("comp1").geom("geom1").feature("sel2").selection("selection").init(1);
    model.component("comp1").geom("geom1").feature("sel2").selection("selection").set("dif1", 9, 61, 78, 79);

    model.component("comp1").view("view1").set("renderwireframe", false);

    model.component("comp1").geom("geom1").feature("sel2").set("contributeto", "csel4");
    model.component("comp1").geom("geom1").run("sel2");
    model.component("comp1").geom("geom1").create("sel3", "ExplicitSelection");
    model.component("comp1").geom("geom1").feature("sel3").selection("selection").set("dif1", 2);
    model.component("comp1").geom("geom1").feature("sel3").label("\u65cb\u8f6c\u6d41\u4f53\u57df");
    model.component("comp1").geom("geom1").run("fin");
    model.component("comp1").geom("geom1").create("ige1", "IgnoreEdges");
    model.component("comp1").geom("geom1").feature("ige1").selection("input").named("csel4");
    model.component("comp1").geom("geom1").feature("ige1").set("ignorevtx", false);
    model.component("comp1").geom("geom1").run("ige1");

    model.title("\u6a21\u5757\u5316\u5c42\u6d41\u6405\u62cc\u5668 - \u6a21\u677f\u6587\u4ef6");

    model
         .description("\u672c\u4f8b\u662f\u4e00\u4e2a\u6a21\u677f MPH \u6587\u4ef6\uff0c\u7528\u4e8e\u5206\u6790\u201c\u6a21\u5757\u5316\u6405\u62cc\u5668\u201d\u6a21\u578b\u7684\u5c42\u6d41\u60c5\u51b5\u3002\u51e0\u4f55\u4f53\u4e3a Rushton \u53f6\u8f6e\u4e0e\u5e73\u5e95\u91dc\u7684\u7ec4\u5408\u7ed3\u6784\u3002\u5176\u4e2d\u4ece\u201c\u96f6\u4ef6\u5e93\u201d\u5bfc\u5165\u7528\u4e8e\u6784\u5efa\u53f6\u8f6e\u548c\u5bb9\u5668\u7684\u51e0\u4f55\u5b50\u5e8f\u5217\u3002");

    model.label("modular_mixer_geom.mph");

    return model;
  }

  public static void main(String[] args) {
    run();
  }

}
