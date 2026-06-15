/*
 * schmidt_cassegrain_telescope_geom_sequence.java
 */

import com.comsol.model.*;
import com.comsol.model.util.*;

/** Model exported on Apr 30 2026, 12:19 by COMSOL 6.3.0.290. */
public class schmidt_cassegrain_telescope_geom_sequence {

  public static Model run() {
    Model model = ModelUtil.create("Model");

    model
         .modelPath("D:\\Program Files\\COMSOL\\COMSOL63\\Multiphysics\\applications\\Ray_Optics_Module\\Lenses_Cameras_and_Telescopes");

    model.component().create("comp1", true);

    model.component("comp1").geom().create("geom1", 3);
    model.component("comp1").geom("geom1").geomRep("comsol");

    model.component("comp1").mesh().create("mesh1");
    model.component("comp1").mesh("mesh1").contribute("geom/detail", true);

    model.component("comp1").geom("geom1")
         .label("\u65bd\u5bc6\u7279-\u5361\u585e\u683c\u6797\u671b\u8fdc\u955c\u51e0\u4f55\u5e8f\u5217");
    model.component("comp1").geom("geom1").lengthUnit("mm");

//    To import content from file, use:
//    model.param().loadFile("FILENAME");
    model.param().set("nix", "0.0", "\u5168\u5c40\u5149\u8f74\uff0cx \u5206\u91cf");
    model.param().set("niy", "0.0", "\u5168\u5c40\u5149\u8f74\uff0cy \u5206\u91cf");
    model.param().set("niz", "-1.0", "\u5168\u5c40\u5149\u8f74\uff0cz \u5206\u91cf");
    model.param().set("R1_corr", "0", "\u6821\u6b63\u5668\u66f2\u7387\u534a\u5f84\uff0c\u8868\u9762 1");
    model.param()
         .set("R2_corr", "-56118.2800[mm]", "\u6821\u6b63\u5668\u66f2\u7387\u534a\u5f84\uff0c\u8868\u9762 2");
    model.param()
         .set("A04_corr", "6.431005e-10", "\u6821\u6b63\u5668\u7b2c 4 \u9636\u975e\u7403\u9762\u9879\uff0c\u8868\u9762 2");
    model.param()
         .set("A06_corr", "3.113976e-16", "\u6821\u6b63\u5668\u7b2c 6 \u9636\u975e\u7403\u9762\u9879\uff0c\u8868\u9762 2");
    model.param().set("Tc_corr", "4.0000[mm]", "\u6821\u6b63\u5668\u4e2d\u5fc3\u539a\u5ea6");
    model.param().set("d0_corr", "210.0000[mm]", "\u6821\u6b63\u5668\u5916\u5f84");
    model.param().set("R_sec", "252.6581[mm]", "\u526f\u955c\u66f2\u7387\u534a\u5f84");
    model.param().set("d0_sec", "60.0000[mm]", "\u526f\u955c\u5916\u5f84");
    model.param().set("dc_sec", "53.9204[mm]", "\u526f\u955c\u51c0\u76f4\u5f84");
    model.param().set("Tc_sec", "7.0000[mm]", "\u526f\u955c\u4e2d\u5fc3\u539a\u5ea6");
    model.param()
         .set("z_sec", "7.0000[mm]", "\u526f\u955c z \u5750\u6807\uff0c\u76f8\u5bf9\u4e8e\u6821\u6b63\u5668\u8868\u9762 2");
    model.param().set("R_prim", "-812.8000[mm]", "\u4e3b\u955c\u66f2\u7387\u534a\u5f84");
    model.param().set("d0_prim", "215.0000[mm]", "\u4e3b\u955c\u5916\u5f84");
    model.param().set("d1_prim", "206.1674[mm]", "\u4e3b\u955c\u9762\u76f4\u5f84");
    model.param().set("dh_prim", "50.0000[mm]", "\u4e3b\u955c\u5b54\u5f84");
    model.param().set("Tc_prim", "20.0000[mm]", "\u4e3b\u955c\u4e2d\u5fc3\u539a\u5ea6");
    model.param().set("z_prim", "-303.8701[mm]", "\u4e3b\u955c z \u5750\u6807\uff0c\u76f8\u5bf9\u4e8e\u526f\u955c");
    model.param().set("d_img", "40.0000[mm]", "\u50cf\u5e73\u9762\u76f4\u5f84");
    model.param()
         .set("z_img", "200.0000[mm]", "\u50cf\u5e73\u9762 z \u5750\u6807\uff0c\u76f8\u5bf9\u4e8e\u4e3b\u955c\u9876\u70b9");
    model.param().set("d_obs", "70.0000[mm]", "\u906e\u6321\u6bd4\u76f4\u5f84");
    model.param()
         .set("z_obs", "-10.0000[mm]", "\u906e\u6321\u6bd4 z \u5750\u6807\uff0c\u76f8\u5bf9\u4e8e\u6821\u6b63\u5668\u8868\u9762 1");

    model.geom()
         .load(new String[]{"part1"}, "Ray_Optics_Module\\3D\\Aspheric_Lenses\\aspheric_even_lens_3d.mph", new String[]{"part1"});
    model.component("comp1").geom("geom1").create("pi1", "PartInstance");
    model.component("comp1").geom("geom1").feature("pi1").set("selkeepnoncontr", false);
    model.component("comp1").geom("geom1").feature("pi1").set("part", "part1");
    model.component("comp1").geom("geom1").feature("pi1").label("\u6821\u6b63\u5668");
    model.component("comp1").geom("geom1").feature("pi1").setEntry("inputexpr", "R1", "R1_corr");
    model.component("comp1").geom("geom1").feature("pi1").setEntry("inputexpr", "R2", "R2_corr");
    model.component("comp1").geom("geom1").feature("pi1").setEntry("inputexpr", "A04_2", "A04_corr");
    model.component("comp1").geom("geom1").feature("pi1").setEntry("inputexpr", "A06_2", "A06_corr");
    model.component("comp1").geom("geom1").feature("pi1").setEntry("inputexpr", "Tc", "Tc_corr");
    model.component("comp1").geom("geom1").feature("pi1").setEntry("inputexpr", "d0", "d0_corr");
    model.component("comp1").geom("geom1").feature("pi1").setEntry("inputexpr", "d1", 0);
    model.component("comp1").geom("geom1").feature("pi1").setEntry("inputexpr", "d2", 0);
    model.component("comp1").geom("geom1").feature("pi1").setEntry("inputexpr", "d1_clear", 0);
    model.component("comp1").geom("geom1").feature("pi1").setEntry("inputexpr", "d2_clear", 0);
    model.component("comp1").geom("geom1").feature("pi1").setEntry("inputexpr", "nix", "nix");
    model.component("comp1").geom("geom1").feature("pi1").setEntry("inputexpr", "niy", "niy");
    model.component("comp1").geom("geom1").feature("pi1").setEntry("inputexpr", "niz", "niz");
    model.geom()
         .load(new String[]{"part2"}, "Ray_Optics_Module\\3D\\Mirrors\\spherical_mirror_3d.mph", new String[]{"part1"});
    model.component("comp1").geom("geom1").run("pi1");
    model.component("comp1").geom("geom1").create("pi2", "PartInstance");
    model.component("comp1").geom("geom1").feature("pi2").set("selkeepnoncontr", false);
    model.component("comp1").geom("geom1").feature("pi2").set("part", "part2");
    model.component("comp1").geom("geom1").feature("pi2").label("\u526f\u955c");
    model.component("comp1").geom("geom1").feature("pi2").setEntry("inputexpr", "R", "R_sec");
    model.component("comp1").geom("geom1").feature("pi2").setEntry("inputexpr", "Tc", "Tc_sec");
    model.component("comp1").geom("geom1").feature("pi2").setEntry("inputexpr", "d0", "d0_sec");
    model.component("comp1").geom("geom1").feature("pi2").setEntry("inputexpr", "d1", 0);
    model.component("comp1").geom("geom1").feature("pi2").setEntry("inputexpr", "d_clear", 0);
    model.component("comp1").geom("geom1").feature("pi2").setEntry("inputexpr", "d_hole", 0);
    model.component("comp1").geom("geom1").feature("pi2").set("workplanesrc", "pi1");
    model.component("comp1").geom("geom1").feature("pi2").set("workplane", "wp2");
    model.component("comp1").geom("geom1").feature("pi2").set("displ", new String[]{"0", "0", "z_sec"});
    model.component("comp1").geom("geom1").run("pi2");
    model.component("comp1").geom("geom1").create("pi3", "PartInstance");
    model.component("comp1").geom("geom1").feature("pi3").set("selkeepnoncontr", false);
    model.component("comp1").geom("geom1").feature("pi3").set("part", "part2");
    model.component("comp1").geom("geom1").feature("pi3").label("\u4e3b\u955c");
    model.component("comp1").geom("geom1").feature("pi3").setEntry("inputexpr", "R", "R_prim");
    model.component("comp1").geom("geom1").feature("pi3").setEntry("inputexpr", "Tc", "Tc_prim");
    model.component("comp1").geom("geom1").feature("pi3").setEntry("inputexpr", "d0", "d0_prim");
    model.component("comp1").geom("geom1").feature("pi3").setEntry("inputexpr", "d1", "d1_prim");
    model.component("comp1").geom("geom1").feature("pi3").setEntry("inputexpr", "d_clear", 0);
    model.component("comp1").geom("geom1").feature("pi3").setEntry("inputexpr", "d_hole", "dh_prim");
    model.component("comp1").geom("geom1").feature("pi3").set("workplanesrc", "pi2");
    model.component("comp1").geom("geom1").feature("pi3").set("workplane", "wp1");
    model.component("comp1").geom("geom1").feature("pi3").set("displ", new String[]{"0", "0", "z_prim"});
    model.geom()
         .load(new String[]{"part3"}, "Ray_Optics_Module\\3D\\Apertures_and_Obstructions\\circular_planar_annulus.mph", new String[]{"part1"});
    model.component("comp1").geom("geom1").run("pi3");
    model.component("comp1").geom("geom1").create("pi4", "PartInstance");
    model.component("comp1").geom("geom1").feature("pi4").set("selkeepnoncontr", false);
    model.component("comp1").geom("geom1").feature("pi4").set("part", "part3");
    model.component("comp1").geom("geom1").feature("pi4").label("\u50cf\u5e73\u9762");
    model.component("comp1").geom("geom1").feature("pi4").setEntry("inputexpr", "d0", "d_img");
    model.component("comp1").geom("geom1").feature("pi4").setEntry("inputexpr", "d1", 0);
    model.component("comp1").geom("geom1").feature("pi4").set("workplanesrc", "pi3");
    model.component("comp1").geom("geom1").feature("pi4").set("workplane", "wp1");
    model.component("comp1").geom("geom1").feature("pi4").set("displ", new String[]{"0", "0", "z_img"});
    model.component("comp1").geom("geom1").run("pi4");
    model.component("comp1").geom("geom1").create("pi5", "PartInstance");
    model.component("comp1").geom("geom1").feature("pi5").set("selkeepnoncontr", false);
    model.component("comp1").geom("geom1").feature("pi5").set("part", "part3");
    model.component("comp1").geom("geom1").feature("pi5").label("\u4e2d\u592e\u969c\u788d\u7269");
    model.component("comp1").geom("geom1").feature("pi5").setEntry("inputexpr", "d0", "d_obs");
    model.component("comp1").geom("geom1").feature("pi5").setEntry("inputexpr", "d1", 0);
    model.component("comp1").geom("geom1").feature("pi5").set("workplanesrc", "pi1");
    model.component("comp1").geom("geom1").feature("pi5").set("workplane", "wp1");
    model.component("comp1").geom("geom1").feature("pi5").set("displ", new String[]{"0", "0", "z_obs"});
    model.component("comp1").geom("geom1").runPre("fin");
    model.component("comp1").geom("geom1").feature("pi1").setEntry("selkeepdom", "pi1_sel1", true);
    model.component("comp1").geom("geom1").selection().create("csel1", "CumulativeSelection");
    model.component("comp1").geom("geom1").selection("csel1").label("\u901a\u5149\u5b54\u5f84");
    model.component("comp1").geom("geom1").feature("pi1").setEntry("selcontributetobnd", "pi1_sel3", "csel1");
    model.component("comp1").geom("geom1").feature("pi1").setEntry("selcontributetobnd", "pi1_sel4", "csel1");
    model.component("comp1").geom("geom1").selection().create("csel2", "CumulativeSelection");
    model.component("comp1").geom("geom1").selection("csel2").label("\u969c\u788d\u7269");
    model.component("comp1").geom("geom1").feature("pi1").setEntry("selcontributetobnd", "pi1_sel5", "csel2");
    model.component("comp1").geom("geom1").feature("pi1").setEntry("selcontributetobnd", "pi1_sel6", "csel2");
    model.component("comp1").geom("geom1").feature("pi1").setEntry("selcontributetobnd", "pi1_sel7", "csel2");
    model.component("comp1").geom("geom1").selection().create("csel3", "CumulativeSelection");
    model.component("comp1").geom("geom1").selection("csel3").label("\u53cd\u5c04\u955c");
    model.component("comp1").geom("geom1").feature("pi2").setEntry("selcontributetobnd", "pi2_cylsel1", "csel3");
    model.component("comp1").geom("geom1").feature("pi2").setEntry("selcontributetobnd", "pi2_cylsel2", "csel2");
    model.component("comp1").geom("geom1").feature("pi2").setEntry("selcontributetobnd", "pi2_comsel2", "csel2");
    model.component("comp1").geom("geom1").feature("pi3").setEntry("selcontributetobnd", "pi3_cylsel1", "csel3");
    model.component("comp1").geom("geom1").feature("pi3").setEntry("selcontributetobnd", "pi3_cylsel2", "csel2");
    model.component("comp1").geom("geom1").feature("pi3").setEntry("selcontributetobnd", "pi3_comsel2", "csel2");
    model.component("comp1").geom("geom1").feature("pi4").setEntry("selkeepbnd", "pi4_sel1", true);
    model.component("comp1").geom("geom1").feature("pi5").setEntry("selcontributetobnd", "pi5_sel1", "csel2");

    model.title(null);

    model.description("");

    model.label("schmidt_cassegrain_telescope_geom_sequence.mph");

    return model;
  }

  public static void main(String[] args) {
    run();
  }

}
