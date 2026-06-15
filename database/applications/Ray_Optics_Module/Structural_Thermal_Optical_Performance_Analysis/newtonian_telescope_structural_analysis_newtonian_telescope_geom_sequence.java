/*
 * newtonian_telescope_structural_analysis_newtonian_telescope_geom_sequence.java
 */

import com.comsol.model.*;
import com.comsol.model.util.*;

/** Model exported on Apr 30 2026, 12:22 by COMSOL 6.3.0.290. */
public class newtonian_telescope_structural_analysis_newtonian_telescope_geom_sequence {

  public static Model run() {
    Model model = ModelUtil.create("Model");

    model
         .modelPath("D:\\Program Files\\COMSOL\\COMSOL63\\Multiphysics\\applications\\Ray_Optics_Module\\Structural_Thermal_Optical_Performance_Analysis");

    model.component().create("comp1", true);

    model.component("comp1").geom().create("geom1", 3);
    model.component("comp1").geom("geom1").geomRep("comsol");

    model.component("comp1").mesh().create("mesh1");
    model.component("comp1").mesh("mesh1").contribute("geom/detail", true);

    model.component("comp1").geom("geom1").label("\u725b\u987f\u671b\u8fdc\u955c\u51e0\u4f55\u5e8f\u5217");
    model.component("comp1").geom("geom1").lengthUnit("mm");

//    To import content from file, use:
//    model.param().loadFile("FILENAME");
    model.param().set("nix", "0", "\u5168\u5c40\u5149\u8f74\uff0cx \u5206\u91cf");
    model.param().set("niy", "0", "\u5168\u5c40\u5149\u8f74\uff0cy \u5206\u91cf");
    model.param().set("niz", "1", "\u5168\u5c40\u5149\u8f74\uff0cz \u5206\u91cf");
    model.param().set("d_pupil", "250[mm]", "\u5165\u5c04\u5149\u77b3\u76f4\u5f84");
    model.param().set("f", "1000[mm]", "\u4e3b\u955c\u7126\u8ddd");
    model.param().set("k", "-1.0", "\u4e3b\u955c\u5706\u9525\u5e38\u6570");
    model.param().set("F", "f/d_pupil", "\u4e3b\u955c\u7126\u6bd4");
    model.param()
         .set("f_image", "200[mm]", "\u50cf\u5e73\u9762\u4f4d\u7f6e\uff08\u76f8\u5bf9\u4e8e\u5149\u8f74\uff09");
    model.param().set("d_sec", "f_image/F", "\u526f\u955c\u76f4\u5f84\uff08\u6295\u5f71\uff09");
    model.param()
         .set("delta_sec", "sqrt(2)*d_sec*(d_pupil-d_sec)/(4*(f-f_image))", "\u526f\u955c\u504f\u79fb\u91cf\uff08\u76f8\u5bf9\u4e8e\u5149\u8f74\uff09");
    model.param().set("d_image", "d_sec", "\u50cf\u5e73\u9762\u76f4\u5f84");
    model.param().set("d_clear", "0", "\u4e3b\u955c\u51c0\u76f4\u5f84\uff08\u4f7f\u7528\u5168\u955c\u9762\uff09");
    model.param().set("d1_prim", "260[mm]", "\u4e3b\u955c\u9762\u76f4\u5f84");
    model.param().set("d0_prim", "275[mm]", "\u4e3b\u955c\u5916\u5f84");
    model.param().set("Tc_prim", "35[mm]", "\u4e3b\u955c\u539a\u5ea6");
    model.param().set("Tc_sec", "10[mm]", "\u526f\u955c\u539a\u5ea6");

    model.geom()
         .load(new String[]{"part1"}, "Ray_Optics_Module\\3D\\Mirrors\\conic_mirror_on_axis_3d.mph", new String[]{"part1"});
    model.component("comp1").geom("geom1").create("pi1", "PartInstance");
    model.component("comp1").geom("geom1").feature("pi1").set("selkeepnoncontr", false);
    model.component("comp1").geom("geom1").feature("pi1").set("part", "part1");
    model.component("comp1").geom("geom1").feature("pi1").label("\u4e3b\u955c");
    model.component("comp1").geom("geom1").feature("pi1").setEntry("inputexpr", "R", "-2*f");
    model.component("comp1").geom("geom1").feature("pi1").setEntry("inputexpr", "k", "k");
    model.component("comp1").geom("geom1").feature("pi1").setEntry("inputexpr", "Tc", "Tc_prim");
    model.component("comp1").geom("geom1").feature("pi1").setEntry("inputexpr", "d0", "d0_prim");
    model.component("comp1").geom("geom1").feature("pi1").setEntry("inputexpr", "d1", "d1_prim");
    model.component("comp1").geom("geom1").feature("pi1").setEntry("inputexpr", "d_clear", "d_clear");
    model.component("comp1").geom("geom1").feature("pi1").setEntry("inputexpr", "d_hole", 0);
    model.component("comp1").geom("geom1").feature("pi1").setEntry("inputexpr", "nix", "-nix");
    model.component("comp1").geom("geom1").feature("pi1").setEntry("inputexpr", "niy", "-niy");
    model.component("comp1").geom("geom1").feature("pi1").setEntry("inputexpr", "niz", "-niz");
    model.component("comp1").geom("geom1").selection().create("csel1", "CumulativeSelection");
    model.component("comp1").geom("geom1").selection("csel1").label("\u6240\u6709\u4e3b\u955c\u969c\u788d\u7269");
    model.component("comp1").geom("geom1").selection().create("csel2", "CumulativeSelection");
    model.component("comp1").geom("geom1").selection("csel2").label("\u6240\u6709\u526f\u955c\u969c\u788d\u7269");
    model.component("comp1").geom("geom1").selection().create("csel3", "CumulativeSelection");
    model.component("comp1").geom("geom1").selection("csel3").label("\u63a2\u6d4b\u5668");
    model.component("comp1").geom("geom1").feature("pi1").setEntry("selkeepbnd", "pi1_cylsel1", true);
    model.component("comp1").geom("geom1").feature("pi1").setEntry("selcontributetobnd", "pi1_adjsel1", "csel1");
    model.component("comp1").geom("geom1").feature("pi1").setEntry("selcontributetobnd", "pi1_cylsel2", "csel1");
    model.component("comp1").geom("geom1").feature("pi1").setEntry("selcontributetobnd", "pi1_comsel2", "csel1");
    model.geom()
         .load(new String[]{"part2"}, "Ray_Optics_Module\\3D\\Mirrors\\elliptical_planar_mirror_3d.mph", new String[]{"part1"});
    model.component("comp1").geom("geom1").run("pi1");
    model.component("comp1").geom("geom1").create("pi2", "PartInstance");
    model.component("comp1").geom("geom1").feature("pi2").set("selkeepnoncontr", false);
    model.component("comp1").geom("geom1").feature("pi2").set("part", "part2");
    model.component("comp1").geom("geom1").feature("pi2").label("\u526f\u955c");
    model.component("comp1").geom("geom1").feature("pi2").setEntry("inputexpr", "Tc", "Tc_sec");
    model.component("comp1").geom("geom1").feature("pi2").setEntry("inputexpr", "d0", "1.25*d_sec");
    model.component("comp1").geom("geom1").feature("pi2").setEntry("inputexpr", "dx", "delta_sec");
    model.component("comp1").geom("geom1").feature("pi2").setEntry("inputexpr", "nix", 0);
    model.component("comp1").geom("geom1").feature("pi2").setEntry("inputexpr", "niy", 0);
    model.component("comp1").geom("geom1").feature("pi2").setEntry("inputexpr", "niz", -1);
    model.component("comp1").geom("geom1").feature("pi2").setEntry("inputexpr", "nxx", -1);
    model.component("comp1").geom("geom1").feature("pi2").set("workplanesrc", "pi1");
    model.component("comp1").geom("geom1").feature("pi2").set("workplane", "wp1");
    model.component("comp1").geom("geom1").feature("pi2").set("displ", new String[]{"0", "0", "-(f-f_image)"});
    model.component("comp1").geom("geom1").feature("pi2").setEntry("selkeepbnd", "pi2_cylsel1", true);
    model.component("comp1").geom("geom1").feature("pi2").setEntry("selcontributetobnd", "pi2_adjsel1", "csel2");
    model.component("comp1").geom("geom1").feature("pi2").setEntry("selcontributetobnd", "pi2_comsel1", "csel2");
    model.geom()
         .load(new String[]{"part3"}, "Ray_Optics_Module\\3D\\Apertures_and_Obstructions\\circular_planar_annulus.mph", new String[]{"part1"});
    model.component("comp1").geom("geom1").run("pi2");
    model.component("comp1").geom("geom1").create("pi3", "PartInstance");
    model.component("comp1").geom("geom1").feature("pi3").set("selkeepnoncontr", false);
    model.component("comp1").geom("geom1").feature("pi3").set("part", "part3");
    model.component("comp1").geom("geom1").feature("pi3").label("\u526f\u955c\u969c\u788d\u7269");
    model.component("comp1").geom("geom1").feature("pi3").setEntry("inputexpr", "d0", "1.30*d_sec");
    model.component("comp1").geom("geom1").feature("pi3").setEntry("inputexpr", "d1", 0);
    model.component("comp1").geom("geom1").feature("pi3").set("workplanesrc", "pi2");
    model.component("comp1").geom("geom1").feature("pi3").set("workplane", "wp1");
    model.component("comp1").geom("geom1").feature("pi3").set("displ", new String[]{"-delta_sec", "0", "0"});
    model.component("comp1").geom("geom1").feature("pi3").setIndex("displ", "d_sec", 2);
    model.component("comp1").geom("geom1").feature("pi3").setEntry("selcontributetobnd", "pi3_sel1", "csel2");
    model.component("comp1").geom("geom1").run("pi3");
    model.component("comp1").geom("geom1").create("pi4", "PartInstance");
    model.component("comp1").geom("geom1").feature("pi4").set("selkeepnoncontr", false);
    model.component("comp1").geom("geom1").feature("pi4").set("part", "part3");
    model.component("comp1").geom("geom1").feature("pi4").label("\u50cf\u5e73\u9762");
    model.component("comp1").geom("geom1").feature("pi4").setEntry("inputexpr", "d0", "d_image");
    model.component("comp1").geom("geom1").feature("pi4").setEntry("inputexpr", "d1", 0);
    model.component("comp1").geom("geom1").feature("pi4").set("workplanesrc", "pi2");
    model.component("comp1").geom("geom1").feature("pi4").set("workplane", "wp4");
    model.component("comp1").geom("geom1").feature("pi4").set("displ", new String[]{"0", "0", "-f_image"});
    model.component("comp1").geom("geom1").feature("pi4").setEntry("selcontributetobnd", "pi4_sel1", "csel3");
    model.component("comp1").geom("geom1").runPre("fin");

    model.title(null);

    model.description("");

    model.label("newtonian_telescope_structural_analysis_newtonian_telescope_geom_sequence.mph");

    return model;
  }

  public static void main(String[] args) {
    run();
  }

}
