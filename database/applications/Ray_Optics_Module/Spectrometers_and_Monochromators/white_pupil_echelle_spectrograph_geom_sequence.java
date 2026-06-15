/*
 * white_pupil_echelle_spectrograph_geom_sequence.java
 */

import com.comsol.model.*;
import com.comsol.model.util.*;

/** Model exported on Apr 30 2026, 12:22 by COMSOL 6.3.0.290. */
public class white_pupil_echelle_spectrograph_geom_sequence {

  public static Model run() {
    Model model = ModelUtil.create("Model");

    model
         .modelPath("D:\\Program Files\\COMSOL\\COMSOL63\\Multiphysics\\applications\\Ray_Optics_Module\\Spectrometers_and_Monochromators");

    model.component().create("comp1", true);

    model.component("comp1").geom().create("geom1", 3);
    model.component("comp1").geom("geom1").geomRep("comsol");

    model.component("comp1").mesh().create("mesh1");
    model.component("comp1").mesh("mesh1").contribute("geom/detail", true);

//    To import content from file, use:
//    model.param().loadFile("FILENAME");
    model.param().set("B", "100.0[mm]", "\u51c6\u76f4\u5149\u675f\u76f4\u5f84");
    model.param().set("d_mag", "2.0", "\u767d\u77b3\u7f29\u5c0f\u5e38\u6570\uff08\u5373\uff0cf_1/f_2\uff09");
    model.param().set("f_1", "800[mm]", "\u4e3b\u955c (OAP 1) \u7126\u8ddd");
    model.param().set("f_2", "f_1/d_mag", "\u526f\u955c (OAP 2) \u7126\u8ddd");
    model.param().set("f_clear", "0.95", "\u955c\u9762\u901a\u5149\u5b54\u5f84\u5206\u6570");
    model.param().set("d0_OAP_1", "185.0[mm]", "OAP 1 \u7684\u5916\u5f84");
    model.param().set("dc_OAP_1", "f_clear*d0_OAP_1", "OAP 1 \u7684\u51c0\u76f4\u5f84");
    model.param().set("Tc_OAP_1", "d0_OAP_1/6", "OAP 1 \u7684\u4e2d\u5fc3\u539a\u5ea6");
    model.param().set("d0_OAP_2", "125.0[mm]", "OAP 2 \u7684\u5916\u5f84");
    model.param().set("dc_OAP_2", "f_clear*d0_OAP_2", "OAP 2 \u7684\u51c0\u76f4\u5f84");
    model.param().set("Tc_OAP_2", "d0_OAP_2/6", "OAP 2 \u7684\u4e2d\u5fc3\u539a\u5ea6");
    model.param().set("theta_i", "7.50[deg]", "OAP \u79bb\u8f74\u89d2");
    model.param().set("dx_1", "f_1*tan(theta_i)", "\u5165\u5c04\u5149\u77b3\u79bb\u8f74\u8ddd\u79bb");
    model.param().set("dx_2", "f_2*tan(theta_i)", "\u51fa\u5c04\u5149\u77b3\u79bb\u8f74\u8ddd\u79bb");
    model.param().set("dx_1_nom", "dx_1-f_1*tan(2*gamma_ech)", "\u4e3b\u955c\u79bb\u8f74\u8ddd\u79bb");
    model.param().set("dx_2_nom", "dx_2+f_1*tan(2*gamma_ech)", "\u526f\u955c\u79bb\u8f74\u8ddd\u79bb");
    model.param().set("R_num", "4.0", "\u9636\u68af\u5149\u6805 R#");
    model.param().set("theta_B", "atan(R_num)", "\u9636\u68af\u5149\u6805\u95ea\u8000\u89d2");
    model.param().set("gamma_ech", "-0.65[deg]", "\u9636\u68af\u5149\u6805\u9762\u5916\u89d2");
    model.param().set("W_ech", "1.05*B", "\u9636\u68af\u5149\u6805\u5bbd\u5ea6");
    model.param().set("D_ech", "R_num*W_ech", "\u9636\u68af\u5149\u6805\u6df1\u5ea6");
    model.param().set("H_ech", "D_ech/10", "\u9636\u68af\u5149\u6805\u9ad8\u5ea6");
    model.param().set("lam_xdp", "525.0[nm]", "\u4ea4\u53c9\u8272\u6563\u5149\u6805\u4e2d\u6ce2\u957f");
    model.param().set("T_xdp", "725.0[mm^-1]", "\u4ea4\u53c9\u8272\u6563\u5149\u6805\u7ebf\u9891\u7387");
    model.param().set("sigma_xdp", "1/T_xdp", "\u4ea4\u53c9\u8272\u6563\u5149\u6805\u7ebf\u95f4\u8ddd");
    model.param()
         .set("theta_xdp", "asin(lam_xdp/(2*sigma_xdp))", "\u4ea4\u53c9\u8272\u6563\u5149\u6805\u95ea\u8000\u89d2");
    model.param().set("W_xdp", "1.50*B/d_mag", "\u4ea4\u53c9\u8272\u6563\u5149\u6805\u5bbd\u5ea6");
    model.param().set("D_xdp", "1.25*B/d_mag", "\u4ea4\u53c9\u8272\u6563\u5149\u6805\u6df1\u5ea6");
    model.param().set("H_xdp", "W_xdp/10", "\u4ea4\u53c9\u8272\u6563\u5149\u6805\u9ad8\u5ea6");
    model.param().set("Z_xdp", "-75.0[mm]", "\u4ea4\u53c9\u8272\u6563\u5149\u6805\u4f4d\u79fb");

    model.geom()
         .load(new String[]{"part1"}, "Ray_Optics_Module\\3D\\Mirrors\\conic_mirror_off_axis_3d.mph", new String[]{"part1"});
    model.geom().create("part2", "Part", 3);
    model.geom("part2").geomRep("comsol");
    model.geom("part2").label("\u5149\u6805");
    model.geom("part2").inputParam().set("W", "50[mm]");
    model.geom("part2").inputParam().descr("W", "\u5149\u6805\u5bbd\u5ea6");
    model.geom("part2").inputParam().set("D", "75[mm]");
    model.geom("part2").inputParam().descr("D", "\u5149\u6805\u6df1\u5ea6");
    model.geom("part2").inputParam().set("H", "10[mm]");
    model.geom("part2").inputParam().descr("H", "\u5149\u6805\u9ad8\u5ea6");
    model.geom("part2").create("blk1", "Block");
    model.geom("part2").feature("blk1").set("size", new String[]{"W", "D", "H"});
    model.geom("part2").feature("blk1").set("base", "center");
    model.geom("part2").feature("blk1").set("pos", new String[]{"0", "0", "-H/2"});
    model.geom("part2").run("blk1");
    model.geom("part2").create("sel1", "ExplicitSelection");
    model.geom("part2").feature("sel1").label("\u5168\u90e8");
    model.geom("part2").feature("sel1").selection("selection").set("blk1", 1);
    model.geom("part2").run("sel1");
    model.geom("part2").create("sel2", "ExplicitSelection");
    model.geom("part2").feature("sel2").label("\u5916\u90e8");
    model.geom("part2").feature("sel2").selection("selection").init(2);
    model.geom("part2").feature("sel2").selection("selection").set("blk1", 1, 2, 3, 4, 5, 6);
    model.geom("part2").run("sel2");
    model.geom("part2").create("sel3", "ExplicitSelection");
    model.geom("part2").feature("sel3").label("\u5149\u6805\u9762");
    model.geom("part2").feature("sel3").selection("selection").init(2);
    model.geom("part2").feature("sel3").selection("selection").set("blk1", 4);
    model.geom("part2").run("sel3");
    model.geom("part2").create("difsel1", "DifferenceSelection");
    model.geom("part2").feature("difsel1").label("\u5149\u6805\u969c\u788d\u7269");
    model.geom("part2").feature("difsel1").set("entitydim", 2);
    model.geom("part2").feature("difsel1").set("add", new String[]{"sel2"});
    model.geom("part2").feature("difsel1").set("subtract", new String[]{"sel3"});
    model.component("comp1").geom("geom1").label("\u767d\u77b3\u9636\u68af\u5149\u6805\u51e0\u4f55\u5e8f\u5217");
    model.component("comp1").geom("geom1").lengthUnit("mm");
    model.component("comp1").geom("geom1").create("pt1", "Point");
    model.component("comp1").geom("geom1").feature("pt1").label("\u5165\u5c04\u70b9");
    model.component("comp1").geom("geom1").feature("pt1").set("selresult", true);
    model.component("comp1").geom("geom1").run("pt1");
    model.component("comp1").geom("geom1").create("wp1", "WorkPlane");
    model.component("comp1").geom("geom1").feature("wp1").set("unite", true);
    model.component("comp1").geom("geom1").feature("wp1")
         .label("Pre \u03b3 \u9636\u68af\u5149\u6805\u5c0f\u5e73\u9762\u5207\u5e73\u9762");
    model.component("comp1").geom("geom1").feature("wp1").set("planetype", "transformed");
    model.component("comp1").geom("geom1").feature("wp1").set("transdispl", new String[]{"dx_1", "0", "0"});
    model.component("comp1").geom("geom1").feature("wp1").set("transaxistype", "x");
    model.component("comp1").geom("geom1").feature("wp1").set("transrot", "90[deg]");
    model.component("comp1").geom("geom1").run("wp1");
    model.component("comp1").geom("geom1").create("wp2", "WorkPlane");
    model.component("comp1").geom("geom1").feature("wp2").set("unite", true);
    model.component("comp1").geom("geom1").feature("wp2")
         .label("Post \u03b3 \u9636\u68af\u5149\u6805\u5c0f\u5e73\u9762\u5207\u5e73\u9762");
    model.component("comp1").geom("geom1").feature("wp2").set("planetype", "transformed");
    model.component("comp1").geom("geom1").feature("wp2").set("workplane", "wp1");
    model.component("comp1").geom("geom1").feature("wp2").set("transrot", "gamma_ech");
    model.component("comp1").geom("geom1").run("wp2");
    model.component("comp1").geom("geom1").create("wp3", "WorkPlane");
    model.component("comp1").geom("geom1").feature("wp3").set("unite", true);
    model.component("comp1").geom("geom1").feature("wp3").label("\u9636\u68af\u5149\u6805\u6cd5\u5411\u5e73\u9762");
    model.component("comp1").geom("geom1").feature("wp3").set("planetype", "transformed");
    model.component("comp1").geom("geom1").feature("wp3").set("workplane", "wp2");
    model.component("comp1").geom("geom1").feature("wp3").set("transaxistype", "x");
    model.component("comp1").geom("geom1").feature("wp3").set("transrot", "theta_B-90");
    model.component("comp1").geom("geom1").run("wp3");
    model.component("comp1").geom("geom1").create("wp4", "WorkPlane");
    model.component("comp1").geom("geom1").feature("wp4").set("unite", true);
    model.component("comp1").geom("geom1").feature("wp4").label("\u767d\u77b3\u5e73\u9762");
    model.component("comp1").geom("geom1").feature("wp4").set("planetype", "transformed");
    model.component("comp1").geom("geom1").feature("wp4").set("transdispl", new String[]{"-dx_2", "0", "0"});
    model.component("comp1").geom("geom1").feature("wp4").set("transaxistype", "y");
    model.component("comp1").geom("geom1").feature("wp4").set("transrot", "4*gamma_ech");
    model.component("comp1").geom("geom1").run("wp4");
    model.component("comp1").geom("geom1").create("wp5", "WorkPlane");
    model.component("comp1").geom("geom1").feature("wp5").set("unite", true);
    model.component("comp1").geom("geom1").feature("wp5").label("\u4ea4\u53c9\u8272\u6563\u5e73\u9762");
    model.component("comp1").geom("geom1").feature("wp5").set("planetype", "transformed");
    model.component("comp1").geom("geom1").feature("wp5").set("workplane", "wp4");
    model.component("comp1").geom("geom1").feature("wp5").set("transdispl", new String[]{"0", "0", "Z_xdp"});
    model.component("comp1").geom("geom1").feature("wp5").set("transaxistype", "y");
    model.component("comp1").geom("geom1").feature("wp5").set("transrot", "-theta_xdp");
    model.component("comp1").geom("geom1").run("wp5");
    model.component("comp1").geom("geom1").create("wp6", "WorkPlane");
    model.component("comp1").geom("geom1").feature("wp6").set("unite", true);
    model.component("comp1").geom("geom1").feature("wp6").label("\u76f8\u673a\u5e73\u9762");
    model.component("comp1").geom("geom1").feature("wp6").set("planetype", "transformed");
    model.component("comp1").geom("geom1").feature("wp6").set("workplane", "wp5");
    model.component("comp1").geom("geom1").feature("wp6").set("transaxistype", "y");
    model.component("comp1").geom("geom1").feature("wp6").set("transrot", "-theta_xdp");
    model.component("comp1").geom("geom1").run("wp6");
    model.component("comp1").geom("geom1").create("pi1", "PartInstance");
    model.component("comp1").geom("geom1").feature("pi1").set("selkeepnoncontr", false);
    model.component("comp1").geom("geom1").feature("pi1").set("part", "part1");
    model.component("comp1").geom("geom1").feature("pi1").label("\u4e3b\u955c");
    model.component("comp1").geom("geom1").feature("pi1").setEntry("inputexpr", "R", "-2*f_1");
    model.component("comp1").geom("geom1").feature("pi1").setEntry("inputexpr", "Tc", "Tc_OAP_1");
    model.component("comp1").geom("geom1").feature("pi1").setEntry("inputexpr", "d0", "d0_OAP_1");
    model.component("comp1").geom("geom1").feature("pi1").setEntry("inputexpr", "d_clear", "dc_OAP_1");
    model.component("comp1").geom("geom1").feature("pi1").setEntry("inputexpr", "dx", "dx_1_nom");
    model.component("comp1").geom("geom1").feature("pi1").setEntry("inputexpr", "niz", 1);
    model.component("comp1").geom("geom1").feature("pi1").setEntry("inputexpr", "mtype", 1);
    model.component("comp1").geom("geom1").feature("pi1").set("displ", new String[]{"0", "0", "f_1"});
    model.component("comp1").geom("geom1").run("pi1");
    model.component("comp1").geom("geom1").create("pi2", "PartInstance");
    model.component("comp1").geom("geom1").feature("pi2").set("selkeepnoncontr", false);
    model.component("comp1").geom("geom1").feature("pi2").set("part", "part1");
    model.component("comp1").geom("geom1").feature("pi2").label("\u526f\u955c");
    model.component("comp1").geom("geom1").feature("pi2").setEntry("inputexpr", "R", "-2*f_2");
    model.component("comp1").geom("geom1").feature("pi2").setEntry("inputexpr", "Tc", "Tc_OAP_2");
    model.component("comp1").geom("geom1").feature("pi2").setEntry("inputexpr", "d0", "d0_OAP_2");
    model.component("comp1").geom("geom1").feature("pi2").setEntry("inputexpr", "d_clear", "dc_OAP_2");
    model.component("comp1").geom("geom1").feature("pi2").setEntry("inputexpr", "dx", "dx_2_nom");
    model.component("comp1").geom("geom1").feature("pi2").setEntry("inputexpr", "nxx", -1);
    model.component("comp1").geom("geom1").feature("pi2").set("displ", new String[]{"0", "0", "-f_2"});
    model.component("comp1").geom("geom1").run("pi2");
    model.component("comp1").geom("geom1").create("pi3", "PartInstance");
    model.component("comp1").geom("geom1").feature("pi3").set("selkeepnoncontr", false);
    model.component("comp1").geom("geom1").feature("pi3").set("part", "part2");
    model.component("comp1").geom("geom1").feature("pi3").label("\u9636\u68af\u5149\u6805");
    model.component("comp1").geom("geom1").feature("pi3").set("workplane", "wp3");
    model.component("comp1").geom("geom1").feature("pi3").setEntry("inputexpr", "W", "W_ech");
    model.component("comp1").geom("geom1").feature("pi3").setEntry("inputexpr", "D", "D_ech");
    model.component("comp1").geom("geom1").feature("pi3").setEntry("inputexpr", "H", "H_ech");
    model.component("comp1").geom("geom1").run("pi3");
    model.component("comp1").geom("geom1").create("pi4", "PartInstance");
    model.component("comp1").geom("geom1").feature("pi4").set("selkeepnoncontr", false);
    model.component("comp1").geom("geom1").feature("pi4").set("part", "part2");
    model.component("comp1").geom("geom1").feature("pi4").label("\u4ea4\u53c9\u8272\u6563\u5149\u6805\u5165\u53e3");
    model.component("comp1").geom("geom1").feature("pi4").setEntry("inputexpr", "W", "W_xdp");
    model.component("comp1").geom("geom1").feature("pi4").setEntry("inputexpr", "D", "D_xdp");
    model.component("comp1").geom("geom1").feature("pi4").setEntry("inputexpr", "H", "H_xdp");
    model.component("comp1").geom("geom1").feature("pi4").set("workplane", "wp5");
    model.component("comp1").geom("geom1").feature("pi4").set("rot", 90);
    model.component("comp1").geom("geom1").run("pi4");
    model.component("comp1").geom("geom1").create("pi5", "PartInstance");
    model.component("comp1").geom("geom1").feature("pi5").set("selkeepnoncontr", false);
    model.component("comp1").geom("geom1").feature("pi5").set("part", "part2");
    model.component("comp1").geom("geom1").feature("pi5").label("\u4ea4\u53c9\u8272\u6563\u5149\u6805\u51fa\u53e3");
    model.component("comp1").geom("geom1").feature("pi5").setEntry("inputexpr", "W", "W_xdp");
    model.component("comp1").geom("geom1").feature("pi5").setEntry("inputexpr", "D", "D_xdp");
    model.component("comp1").geom("geom1").feature("pi5").setEntry("inputexpr", "H", "H_xdp");
    model.component("comp1").geom("geom1").feature("pi5").set("workplanesrc", "pi4");
    model.component("comp1").geom("geom1").feature("pi5").set("axistype", "y");
    model.component("comp1").geom("geom1").feature("pi5").set("rot", 180);
    model.component("comp1").geom("geom1")
         .insertFile("white_pupil_echelle_spectrograph_petzval_lens_geom_sequence.mph", "geom1");
    model.component("comp1").geom("geom1").feature("pi6").set("workplane", "wp6");
    model.component("comp1").geom("geom1").feature("pi6").set("displ", new String[]{"0", "0", "30[mm]"});
    model.component("comp1").geom("geom1").run("pi15");
    model.component("comp1").geom("geom1").create("sca1", "Scale");
    model.component("comp1").geom("geom1").feature("sca1").selection("input")
         .set("pi10", "pi11", "pi12", "pi13", "pi14", "pi15", "pi6", "pi7", "pi8", "pi9");
    model.component("comp1").geom("geom1").feature("sca1").set("isotropic", "1.50");
    model.component("comp1").geom("geom1").feature("sca1").set("workplanesrc", "pi6");

    model.param().set("d1_S", "37.5[mm]");
    model.param().descr("d1_S", "\u5149\u9611\u5b54\u5f84\uff08\u5df2\u8c03\u6574\uff09");
    model.param().set("T_5", "46.819[mm]");
    model.param().descr("T_5", "L4 \u5230 L5 \u7684\u95f4\u8ddd\uff08\u5df2\u8c03\u6574\uff09");
    model.param().set("R1_6", "-51.791[mm]");
    model.param()
         .descr("R1_6", "L5\uff0c\u8868\u9762 1 \u7684\u66f2\u7387\u534a\u5f84\uff08\u5df2\u8c03\u6574\uff09");
    model.param().set("T_6", "1.900[mm]");
    model.param().descr("T_6", "L5 \u5230\u63a2\u6d4b\u5668\u7684\u95f4\u8ddd\uff08\u5df2\u8c03\u6574\uff09");

    model.component("comp1").geom("geom1").feature("pi8").setEntry("selcontributetobnd", "pi8_sel1", "csel7");
    model.component("comp1").geom("geom1").feature("pi12").setEntry("inputexpr", "w0", "20[mm]");
    model.component("comp1").geom("geom1").feature("pi12").setEntry("inputexpr", "h0", "20[mm]");
    model.component("comp1").geom("geom1").feature("pi12").setEntry("selkeepbnd", "pi12_sel1", true);
    model.component("comp1").geom("geom1").feature("pi12").setEntry("selcontributetobnd", "pi12_sel1", "none");
    model.component("comp1").geom("geom1").run("fin");
    model.component("comp1").geom("geom1").selection().create("csel10", "CumulativeSelection");
    model.component("comp1").geom("geom1").selection("csel10").label("\u53cd\u5c04\u955c");
    model.component("comp1").geom("geom1").selection().create("csel11", "CumulativeSelection");
    model.component("comp1").geom("geom1").selection("csel11")
         .label("\u969c\u788d\u7269\uff08\u5149\u6805\u548c\u53cd\u5c04\u955c\uff09");
    model.component("comp1").geom("geom1").selection().create("csel12", "CumulativeSelection");
    model.component("comp1").geom("geom1").selection("csel12").label("\u5149\u6805\u6750\u6599");
    model.component("comp1").geom("geom1").feature("pi1").setEntry("selcontributetobnd", "pi1_cylsel2", "csel10");
    model.component("comp1").geom("geom1").feature("pi1").setEntry("selcontributetobnd", "pi1_adjsel1", "csel11");
    model.component("comp1").geom("geom1").feature("pi1").setEntry("selcontributetobnd", "pi1_cylsel3", "csel11");
    model.component("comp1").geom("geom1").feature("pi1").setEntry("selcontributetobnd", "pi1_comsel2", "csel11");
    model.component("comp1").geom("geom1").feature("pi2").setEntry("selcontributetobnd", "pi2_cylsel2", "csel10");
    model.component("comp1").geom("geom1").feature("pi2").setEntry("selcontributetobnd", "pi2_adjsel1", "csel11");
    model.component("comp1").geom("geom1").feature("pi2").setEntry("selcontributetobnd", "pi2_cylsel3", "csel11");
    model.component("comp1").geom("geom1").feature("pi2").setEntry("selcontributetobnd", "pi2_comsel2", "csel11");
    model.component("comp1").geom("geom1").feature("pi3").setEntry("selkeepbnd", "pi3_sel3", true);
    model.component("comp1").geom("geom1").feature("pi3").setEntry("selcontributetobnd", "pi3_difsel1", "csel11");
    model.component("comp1").geom("geom1").feature("pi4").setEntry("selcontributetodom", "pi4_sel1", "csel12");
    model.component("comp1").geom("geom1").feature("pi4").setEntry("selkeepbnd", "pi4_sel2", true);
    model.component("comp1").geom("geom1").feature("pi4").setEntry("selkeepbnd", "pi4_sel3", true);
    model.component("comp1").geom("geom1").feature("pi5").setEntry("selcontributetodom", "pi5_sel1", "csel12");
    model.component("comp1").geom("geom1").feature("pi5").setEntry("selkeepbnd", "pi5_sel2", true);
    model.component("comp1").geom("geom1").feature("pi5").setEntry("selkeepbnd", "pi5_sel3", true);
    model.component("comp1").geom("geom1").create("unisel1", "UnionSelection");
    model.component("comp1").geom("geom1").feature("unisel1").label("\u5168\u6298\u5c04");
    model.component("comp1").geom("geom1").feature("unisel1").set("input", new String[]{"csel12", "sel1"});

    model.title(null);

    model.description("");

    model.label("white_pupil_echelle_spectrograph_geom_sequence.mph");

    return model;
  }

  public static void main(String[] args) {
    run();
  }

}
