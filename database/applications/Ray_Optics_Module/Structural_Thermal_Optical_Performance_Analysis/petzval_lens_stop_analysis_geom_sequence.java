/*
 * petzval_lens_stop_analysis_geom_sequence.java
 */

import com.comsol.model.*;
import com.comsol.model.util.*;

/** Model exported on Apr 30 2026, 12:23 by COMSOL 6.3.0.290. */
public class petzval_lens_stop_analysis_geom_sequence {

  public static Model run() {
    Model model = ModelUtil.create("Model");

    model
         .modelPath("D:\\Program Files\\COMSOL\\COMSOL63\\Multiphysics\\applications\\Ray_Optics_Module\\Structural_Thermal_Optical_Performance_Analysis");

    model.component().create("comp1", true);

    model.component("comp1").geom().create("geom1", 3);
    model.component("comp1").geom("geom1").geomRep("comsol");

    model.component("comp1").mesh().create("mesh1");
    model.component("comp1").mesh("mesh1").contribute("geom/detail", true);

    model.component("comp1").geom("geom1").lengthUnit("mm");
    model.component("comp1").geom("geom1").label("Petzval \u900f\u955c STOP \u5206\u6790\u51e0\u4f55\u5e8f\u5217");
    model.component("comp1").geom("geom1")
         .insertFile("petzval_lens_stop_analysis_petzval_lens_geom_sequence.mph", "geom1");

//    To import content from file, use:
//    model.param().loadFile("FILENAME");
    model.param().set("tS_1", "1[mm]", "\u900f\u955c\u7ec4 1 \u652f\u67b6\u539a\u5ea6");
    model.param().set("tS_2", "1[mm]", "\u900f\u955c\u7ec4 1 \u652f\u67b6\u539a\u5ea6");
    model.param().set("tS_3", "1[mm]", "\u900f\u955c\u7ec4 1 \u652f\u67b6\u539a\u5ea6");
    model.param().set("dS_1", "d0_1+2*tS_1", "\u955c\u7b52 1 \u5185\u5f84");
    model.param().set("dS_2", "d0_4+2*tS_2", "\u955c\u7b52 2 \u5185\u5f84");
    model.param().set("dS_3", "d0_6+2*tS_3", "\u955c\u7b52 3 \u5185\u5f84");
    model.param().set("d1_clear_1", "0");
    model.param().set("d0_S", "dS_1");
    model.param().set("d0_D", "25.0[mm]");

    model.component("comp1").geom("geom1").run("fin");
    model.geom().create("part4", "Part", 3);
    model.geom("part4").geomRep("comsol");
    model.geom("part4").label("\u955c\u7b52");
    model.geom("part4").inputParam().set("L", "50.0[mm]");
    model.geom("part4").inputParam().descr("L", "\u957f\u5ea6");
    model.geom("part4").inputParam().set("D_in", "50.0[mm]");
    model.geom("part4").inputParam().descr("D_in", "\u5185\u5f84");
    model.geom("part4").inputParam().set("T_wall", "3.0[mm]");
    model.geom("part4").inputParam().descr("T_wall", "\u58c1\u539a");
    model.geom("part4").inputParam().set("D1_out", "65.0[mm]");
    model.geom("part4").inputParam().descr("D1_out", "\u524d\u73af\u5916\u5f84");
    model.geom("part4").inputParam().set("D1_in", "45.0[mm]");
    model.geom("part4").inputParam().descr("D1_in", "\u524d\u73af\u5185\u5f84");
    model.geom("part4").inputParam().set("D2_out", "75.0[mm]");
    model.geom("part4").inputParam().descr("D2_out", "\u540e\u73af\u5916\u5f84");
    model.geom("part4").inputParam().set("D2_in", "45.0[mm]");
    model.geom("part4").inputParam().descr("D2_in", "\u540e\u73af\u5185\u5f84");
    model.geom("part4").inputParam().set("L1", "3.0[mm]");
    model.geom("part4").inputParam().descr("L1", "\u524d\u73af\u539a\u5ea6");
    model.geom("part4").inputParam().set("L2", "3.0[mm]");
    model.geom("part4").inputParam().descr("L2", "\u540e\u73af\u539a\u5ea6");
    model.geom("part4").create("pi1", "PartInstance");
    model.geom("part4").feature("pi1").set("selkeepnoncontr", false);
    model.geom("part4").feature("pi1").set("part", "part2");
    model.geom("part4").feature("pi1").label("\u4e2d\u592e\u955c\u7b52\u73af");
    model.geom("part4").feature("pi1").setEntry("inputexpr", "d0", "D_in+2*T_wall");
    model.geom("part4").feature("pi1").setEntry("inputexpr", "d1", "D_in");
    model.geom("part4").feature("pi1").set("displ", new String[]{"0", "0", "L1"});
    model.geom("part4").run("pi1");
    model.geom("part4").feature().create("ext1", "Extrude");
    model.geom("part4").feature("ext1").label("\u4e2d\u592e\u955c\u7b52");
    model.geom("part4").feature("ext1").selection("inputface").set("pi1", 1);
    model.geom("part4").feature("ext1").setIndex("distance", "L-(L1+L2)", 0);
    model.geom("part4").run("ext1");
    model.geom("part4").create("pi2", "PartInstance");
    model.geom("part4").feature("pi2").set("selkeepnoncontr", false);
    model.geom("part4").feature("pi2").set("part", "part2");
    model.geom("part4").feature("pi2").label("\u524d\u73af\u73af\u5f62\u9762");
    model.geom("part4").feature("pi2").setEntry("inputexpr", "d0", "D1_out");
    model.geom("part4").feature("pi2").setEntry("inputexpr", "d1", "D1_in");
    model.geom("part4").run("pi2");
    model.geom("part4").feature().create("ext2", "Extrude");
    model.geom("part4").feature("ext2").label("\u524d\u73af");
    model.geom("part4").feature("ext2").selection("inputface").set("pi2", 1);
    model.geom("part4").feature("ext2").setIndex("distance", "L1", 0);
    model.geom("part4").run("ext2");
    model.geom("part4").create("pi3", "PartInstance");
    model.geom("part4").feature("pi3").set("selkeepnoncontr", false);
    model.geom("part4").feature("pi3").set("part", "part2");
    model.geom("part4").feature("pi3").label("\u540e\u73af\u73af\u5f62\u9762");
    model.geom("part4").feature("pi3").setEntry("inputexpr", "d0", "D2_out");
    model.geom("part4").feature("pi3").setEntry("inputexpr", "d1", "D2_in");
    model.geom("part4").feature("pi3").set("workplanesrc", "pi2");
    model.geom("part4").feature("pi3").set("workplane", "wp1");
    model.geom("part4").feature("pi3").set("displ", new String[]{"0", "0", "L"});
    model.geom("part4").run("pi3");
    model.geom("part4").feature().create("ext3", "Extrude");
    model.geom("part4").feature("ext3").label("\u540e\u73af");
    model.geom("part4").feature("ext3").selection("inputface").set("pi3", 1);
    model.geom("part4").feature("ext3").setIndex("distance", "L2", 0);
    model.geom("part4").feature("ext3").set("reverse", true);
    model.geom("part4").run("ext3");
    model.geom("part4").create("uni1", "Union");
    model.geom("part4").feature("uni1").label("\u5168\u90e8");
    model.geom("part4").feature("uni1").selection("input").set("ext1", "ext2", "ext3");
    model.geom("part4").feature("uni1").set("selresult", true);
    model.geom("part4").run("uni1");
    model.geom("part4").create("wp1", "WorkPlane");
    model.geom("part4").feature("wp1").set("unite", true);
    model.geom("part4").feature("wp1").label("\u524d\u5e73\u9762");
    model.geom("part4").feature("wp1").set("planetype", "transformed");
    model.geom("part4").feature("wp1").set("workplanesrc", "pi2");
    model.geom("part4").feature("wp1").set("workplane", "wp1");
    model.geom("part4").run("wp1");
    model.geom("part4").create("wp2", "WorkPlane");
    model.geom("part4").feature("wp2").set("unite", true);
    model.geom("part4").feature("wp2").label("\u540e\u5e73\u9762");
    model.geom("part4").feature("wp2").set("planetype", "transformed");
    model.geom("part4").feature("wp2").set("workplanesrc", "pi3");
    model.geom("part4").feature("wp2").set("workplane", "wp1");
    model.geom("part4").run("wp2");
    model.component("comp1").geom("geom1").feature("pi8").setEntry("inputexpr", "d0", "dS_1");
    model.component("comp1").geom("geom1").feature("pi9").setEntry("inputexpr", "d0", "dS_2");
    model.component("comp1").geom("geom1").feature("pi10").setEntry("inputexpr", "d0", "dS_3");
    model.component("comp1").geom("geom1").feature("pi10").set("displ", new int[]{0, 0, 4});
    model.component("comp1").geom("geom1").run("pi10");
    model.component("comp1").geom("geom1").feature().create("ext1", "Extrude");
    model.component("comp1").geom("geom1").feature("ext1").label("\u7b2c 1 \u7ec4\u652f\u67b6");
    model.component("comp1").geom("geom1").feature("ext1").selection("inputface").set("pi8", 1);
    model.component("comp1").geom("geom1").feature("ext1").set("specify", "vertices");
    model.component("comp1").geom("geom1").feature("ext1").selection("vertex").set("pi2", 11);
    model.component("comp1").geom("geom1").selection().create("csel10", "CumulativeSelection");
    model.component("comp1").geom("geom1").selection("csel10").label("\u652f\u67b6");
    model.component("comp1").geom("geom1").feature("ext1").set("contributeto", "csel10");
    model.component("comp1").geom("geom1").run("ext1");
    model.component("comp1").geom("geom1").feature().create("ext2", "Extrude");
    model.component("comp1").geom("geom1").feature("ext2").label("\u7b2c 2 \u7ec4\u652f\u67b6");
    model.component("comp1").geom("geom1").feature("ext2").selection("inputface").set("pi9", 1);
    model.component("comp1").geom("geom1").feature("ext2").set("specify", "vertices");
    model.component("comp1").geom("geom1").feature("ext2").selection("vertex").set("pi5", 11);
    model.component("comp1").geom("geom1").feature("ext2").set("contributeto", "csel10");
    model.component("comp1").geom("geom1").run("ext2");
    model.component("comp1").geom("geom1").feature().create("ext3", "Extrude");
    model.component("comp1").geom("geom1").feature("ext3").label("\u7b2c 3 \u7ec4\u652f\u67b6");
    model.component("comp1").geom("geom1").feature("ext3").selection("inputface").set("pi10", 1);
    model.component("comp1").geom("geom1").feature("ext3").set("specify", "vertices");
    model.component("comp1").geom("geom1").feature("ext3").selection("vertex").set("pi6", 12);
    model.component("comp1").geom("geom1").feature("ext3").set("contributeto", "csel10");
    model.component("comp1").geom("geom1").run("fin");
    model.component("comp1").geom("geom1").run("ext3");
    model.component("comp1").geom("geom1").create("pi11", "PartInstance");
    model.component("comp1").geom("geom1").feature("pi11").set("selkeepnoncontr", false);
    model.component("comp1").geom("geom1").feature("pi11").set("part", "part4");
    model.component("comp1").geom("geom1").feature("pi11").set("workplanepart", "wp1");
    model.component("comp1").geom("geom1").feature("pi11").set("workplanesrc", "pi1");
    model.component("comp1").geom("geom1").feature("pi11").set("workplane", "wp1");
    model.component("comp1").geom("geom1").feature("pi11").setEntry("inputexpr", "L", "T_1+Tc_1+Tc_2+T_2+6[mm]");
    model.component("comp1").geom("geom1").feature("pi11").setEntry("inputexpr", "D_in", "dS_1");
    model.component("comp1").geom("geom1").feature("pi11").setEntry("inputexpr", "D1_out", "70.0[mm]");
    model.component("comp1").geom("geom1").feature("pi11").setEntry("inputexpr", "D1_in", "dS_1");
    model.component("comp1").geom("geom1").feature("pi11").setEntry("inputexpr", "D2_out", "85.0[mm]");
    model.component("comp1").geom("geom1").feature("pi11").setEntry("inputexpr", "D2_in", "d1_S");
    model.component("comp1").geom("geom1").feature("pi11").set("displ", new String[]{"0", "0", "-3.0[mm]"});
    model.component("comp1").geom("geom1").feature("pi11").setEntry("selkeepdom", "pi11_uni1.dom", true);
    model.component("comp1").geom("geom1").run("pi11");
    model.component("comp1").geom("geom1").create("cone1", "Cone");
    model.component("comp1").geom("geom1").feature("cone1").set("specifytop", "radius");
    model.component("comp1").geom("geom1").feature("cone1").set("workplanesrc", "pi11");
    model.component("comp1").geom("geom1").feature("cone1").set("workplane", "wp2");
    model.component("comp1").geom("geom1").feature("cone1").set("h", "3[mm]");
    model.component("comp1").geom("geom1").feature("cone1").set("r", "d1_S/2");
    model.component("comp1").geom("geom1").feature("cone1").set("rtop", "dS_2/2");
    model.component("comp1").geom("geom1").feature("cone1").set("pos", new String[]{"0", "0", "-3[mm]"});
    model.component("comp1").geom("geom1").run("cone1");
    model.component("comp1").geom("geom1").create("dif1", "Difference");
    model.component("comp1").geom("geom1").feature("dif1").selection("input").set("pi11");
    model.component("comp1").geom("geom1").feature("dif1").selection("input2").set("cone1");
    model.component("comp1").geom("geom1").run("dif1");
    model.component("comp1").geom("geom1").create("pi12", "PartInstance");
    model.component("comp1").geom("geom1").feature("pi12").set("selkeepnoncontr", false);
    model.component("comp1").geom("geom1").feature("pi12").set("part", "part4");
    model.component("comp1").geom("geom1").feature("pi12").set("workplanepart", "wp1");
    model.component("comp1").geom("geom1").feature("pi12").set("workplanesrc", "pi11");
    model.component("comp1").geom("geom1").feature("pi12").set("workplane", "wp2");
    model.component("comp1").geom("geom1").feature("pi12").setEntry("inputexpr", "L", "T_3+Tc_4+Tc_5+2[mm]");
    model.component("comp1").geom("geom1").feature("pi12").setEntry("inputexpr", "D_in", "dS_2");
    model.component("comp1").geom("geom1").feature("pi12").setEntry("inputexpr", "D1_out", "85.0[mm]");
    model.component("comp1").geom("geom1").feature("pi12").setEntry("inputexpr", "D1_in", "dS_2");
    model.component("comp1").geom("geom1").feature("pi12").setEntry("inputexpr", "D2_out", "65.0[mm]");
    model.component("comp1").geom("geom1").feature("pi12").setEntry("inputexpr", "D2_in", "d2_clear_5+3[mm]");
    model.component("comp1").geom("geom1").feature("pi12").setEntry("selkeepdom", "pi12_uni1.dom", true);
    model.component("comp1").geom("geom1").run("pi12");
    model.component("comp1").geom("geom1").create("pi13", "PartInstance");
    model.component("comp1").geom("geom1").feature("pi13").set("selkeepnoncontr", false);
    model.component("comp1").geom("geom1").feature("pi13").set("part", "part4");
    model.component("comp1").geom("geom1").feature("pi13").set("workplanepart", "wp1");
    model.component("comp1").geom("geom1").feature("pi13").set("workplanesrc", "pi12");
    model.component("comp1").geom("geom1").feature("pi13").set("workplane", "wp2");
    model.component("comp1").geom("geom1").feature("pi13").setEntry("inputexpr", "L", "T_5+Tc_6+T_6");
    model.component("comp1").geom("geom1").feature("pi13").setEntry("inputexpr", "D_in", "dS_3");
    model.component("comp1").geom("geom1").feature("pi13").setEntry("inputexpr", "D1_in", "d2_clear_5+3[mm]");
    model.component("comp1").geom("geom1").feature("pi13").setEntry("inputexpr", "D2_out", "60.0[mm]");
    model.component("comp1").geom("geom1").feature("pi13").setEntry("inputexpr", "D2_in", "dS_3");
    model.component("comp1").geom("geom1").feature("pi13").setEntry("inputexpr", "L2", "5.0[mm]");
    model.component("comp1").geom("geom1").feature("pi13").setEntry("selkeepdom", "pi13_uni1.dom", true);
    model.component("comp1").geom("geom1").run("pi13");
    model.component("comp1").geom("geom1").feature().create("ext4", "Extrude");
    model.component("comp1").geom("geom1").feature("ext4").label("\u63a2\u6d4b\u5668");
    model.component("comp1").geom("geom1").feature("ext4").selection("inputface").named("csel9");
    model.component("comp1").geom("geom1").run("ext4");
    model.component("comp1").geom("geom1").create("cyl1", "Cylinder");
    model.component("comp1").geom("geom1").feature("cyl1").label("\u63a2\u6d4b\u5668\u5e95\u5ea7");
    model.component("comp1").geom("geom1").feature("cyl1").set("r", "1.5[mm]");
    model.component("comp1").geom("geom1").feature("cyl1").set("h", "4.0[mm]");
    model.component("comp1").geom("geom1").feature("cyl1").set("workplanesrc", "pi13");
    model.component("comp1").geom("geom1").feature("cyl1").set("workplane", "wp2");
    model.component("comp1").geom("geom1").feature("cyl1").set("pos", new String[]{"0", "0", "-4.0[mm]"});
    model.component("comp1").geom("geom1").run("cyl1");
    model.component("comp1").geom("geom1").create("cyl2", "Cylinder");
    model.component("comp1").geom("geom1").feature("cyl2").label("\u63a2\u6d4b\u5668\u5e95\u5ea7\u80cc\u90e8");
    model.component("comp1").geom("geom1").feature("cyl2").set("r", "30.0[mm]");
    model.component("comp1").geom("geom1").feature("cyl2").set("h", "3.0[mm]");
    model.component("comp1").geom("geom1").feature("cyl2").set("workplanesrc", "pi13");
    model.component("comp1").geom("geom1").feature("cyl2").set("workplane", "wp2");
    model.component("comp1").geom("geom1").run("cyl2");
    model.component("comp1").geom("geom1").create("uni1", "Union");
    model.component("comp1").geom("geom1").feature("uni1").label("\u63a2\u6d4b\u5668\u7ec4\u4ef6");
    model.component("comp1").geom("geom1").feature("uni1").selection("input").set("cyl1", "cyl2", "ext4");
    model.component("comp1").geom("geom1").feature("uni1").set("selresult", true);
    model.component("comp1").geom("geom1").run("uni1");
    model.component("comp1").geom("geom1").create("pi14", "PartInstance");
    model.component("comp1").geom("geom1").feature("pi14").set("selkeepnoncontr", false);
    model.component("comp1").geom("geom1").feature("pi14").set("part", "part2");
    model.component("comp1").geom("geom1").feature("pi14").label("\u521a\u6027\u652f\u67b6");
    model.component("comp1").geom("geom1").feature("pi14").setEntry("inputexpr", "d0", "85.0[mm]");
    model.component("comp1").geom("geom1").feature("pi14").setEntry("inputexpr", "d1", "70.0[mm]");
    model.component("comp1").geom("geom1").feature("pi14").set("workplanesrc", "pi12");
    model.component("comp1").geom("geom1").feature("pi14").set("workplane", "wp1");
    model.component("comp1").geom("geom1").feature("pi14").set("displ", new String[]{"0", "0", "5.0[mm]"});
    model.component("comp1").geom("geom1").feature("pi14").setEntry("selkeepbnd", "pi14_sel1", true);
    model.component("comp1").geom("geom1").run("pi14");
    model.component("comp1").geom("geom1").feature().create("ext5", "Extrude");
    model.component("comp1").geom("geom1").feature("ext5").label("\u521a\u6027\u652f\u67b6\u73af");
    model.component("comp1").geom("geom1").feature("ext5").selection("inputface").set("pi14", 1);
    model.component("comp1").geom("geom1").feature("ext5").setIndex("distance", "2.0[mm]", 0);
    model.component("comp1").geom("geom1").feature("ext5").set("reverse", true);
    model.component("comp1").geom("geom1").feature("ext5").set("contributeto", "csel10");
    model.component("comp1").geom("geom1").run("ext5");
    model.component("comp1").geom("geom1").create("comsel1", "ComplementSelection");
    model.component("comp1").geom("geom1").feature("comsel1").label("\u88c5\u914d\u9762");
    model.component("comp1").geom("geom1").feature("comsel1").set("entitydim", 2);
    model.component("comp1").geom("geom1").feature("comsel1").set("input", new String[]{"csel5"});
    model.component("comp1").geom("geom1").runPre("fin");

    model.title(null);

    model.description("");

    model.label("petzval_lens_stop_analysis_geom_sequence.mph");

    return model;
  }

  public static void main(String[] args) {
    run();
  }

}
