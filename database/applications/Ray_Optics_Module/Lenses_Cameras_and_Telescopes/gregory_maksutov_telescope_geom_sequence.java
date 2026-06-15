/*
 * gregory_maksutov_telescope_geom_sequence.java
 */

import com.comsol.model.*;
import com.comsol.model.util.*;

/** Model exported on Apr 30 2026, 12:16 by COMSOL 6.3.0.290. */
public class gregory_maksutov_telescope_geom_sequence {

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
         .label("\u683c\u91cc\u9ad8\u5229-\u9a6c\u514b\u82cf\u6258\u592b\u671b\u8fdc\u955c\u51e0\u4f55\u5e8f\u5217");
    model.component("comp1").geom("geom1").lengthUnit("mm");

//    To import content from file, use:
//    model.param().loadFile("FILENAME");
    model.param().set("nix", "0.0", "\u5168\u5c40\u5149\u8f74\uff0cx \u5206\u91cf");
    model.param().set("niy", "0.0", "\u5168\u5c40\u5149\u8f74\uff0cy \u5206\u91cf");
    model.param().set("niz", "-1.0", "\u5168\u5c40\u5149\u8f74\uff0cz \u5206\u91cf");
    model.param().set("R1_corr", "-268.6151[mm]", "\u6821\u6b63\u5668\u66f2\u7387\u534a\u5f84\uff0c\u8868\u9762 1");
    model.param().set("R2_corr", "-286.1193[mm]", "\u6821\u6b63\u5668\u66f2\u7387\u534a\u5f84\uff0c\u8868\u9762 2");
    model.param().set("Tc_corr", "30.0000[mm]", "\u6821\u6b63\u5668\u4e2d\u5fc3\u539a\u5ea6");
    model.param().set("d0_corr", "225.0000[mm]", "\u6821\u6b63\u5668\u5916\u5f84");
    model.param().set("d1_corr", "205.0000[mm]", "\u6821\u6b63\u5668\u76f4\u5f84\uff0c\u8868\u9762 1");
    model.param().set("d2_corr", "0.0", "\u6821\u6b63\u5668\u76f4\u5f84\uff0c\u8868\u9762 2");
    model.param()
         .set("d1c_corr", "70.0000[mm]", "\u6821\u6b63\u5668\u901a\u5149\u5b54\u5f84\uff0c\u8868\u9762 1\uff08\u906e\u6321\u6bd4\uff09");
    model.param()
         .set("d2c_corr", "60.0000[mm]", "\u6821\u6b63\u5668\u901a\u5149\u5b54\u5f84\uff0c\u8868\u9762 2\uff08\u526f\u955c\uff09");
    model.param().set("R_prim", "-1111.6300[mm]", "\u4e3b\u955c\u66f2\u7387\u534a\u5f84\uff0c\u8868\u9762 1");
    model.param().set("Tc_prim", "25.0000[mm]", "\u4e3b\u955c\u4e2d\u5fc3\u539a\u5ea6");
    model.param().set("d0_prim", "225.0000[mm]", "\u4e3b\u955c\u5916\u5f84");
    model.param().set("d1_prim", "217.5000[mm]", "\u4e3b\u955c\u9762\u76f4\u5f84");
    model.param().set("dc_prim", "0", "\u4e3b\u955c\u901a\u5149\u5b54\u5f84");
    model.param().set("dh_prim", "60.0000[mm]", "\u4e3b\u955c\u4e2d\u5fc3\u5b54\u5f84");
    model.param().set("z_prim", "453.0476[mm]", "\u4e3b\u955c z \u5750\u6807\uff0c\u76f8\u5bf9\u4e8e\u526f\u955c");
    model.param().set("d0_img", "50.0000[mm]", "\u50cf\u5e73\u9762\u76f4\u5f84");
    model.param()
         .set("z_img", "200.0000[mm]", "\u50cf\u5e73\u9762 z \u5750\u6807\uff0c\u76f8\u5bf9\u4e8e\u4e3b\u955c\u9876\u70b9");
    model.param().set("delta_z_img", "0.3890[mm]", "\u50cf\u5e73\u9762 z \u5750\u6807\u504f\u79fb\u91cf");

    model.geom()
         .load(new String[]{"part1"}, "Ray_Optics_Module\\3D\\Spherical_Lenses\\spherical_lens_3d.mph", new String[]{"part1"});
    model.component("comp1").geom("geom1").create("pi1", "PartInstance");
    model.component("comp1").geom("geom1").feature("pi1").set("selkeepnoncontr", false);
    model.component("comp1").geom("geom1").feature("pi1").set("part", "part1");
    model.component("comp1").geom("geom1").feature("pi1").label("\u6821\u6b63\u5668");
    model.component("comp1").geom("geom1").feature("pi1").setEntry("inputexpr", "R1", "R1_corr");
    model.component("comp1").geom("geom1").feature("pi1").setEntry("inputexpr", "R2", "R2_corr");
    model.component("comp1").geom("geom1").feature("pi1").setEntry("inputexpr", "Tc", "Tc_corr");
    model.component("comp1").geom("geom1").feature("pi1").setEntry("inputexpr", "d0", "d0_corr");
    model.component("comp1").geom("geom1").feature("pi1").setEntry("inputexpr", "d1", "d1_corr");
    model.component("comp1").geom("geom1").feature("pi1").setEntry("inputexpr", "d2", "d2_corr");
    model.component("comp1").geom("geom1").feature("pi1").setEntry("inputexpr", "d1_clear", "d1c_corr");
    model.component("comp1").geom("geom1").feature("pi1").setEntry("inputexpr", "d2_clear", "d2c_corr");
    model.component("comp1").geom("geom1").feature("pi1").setEntry("inputexpr", "nix", "nix");
    model.component("comp1").geom("geom1").feature("pi1").setEntry("inputexpr", "niy", "niy");
    model.component("comp1").geom("geom1").feature("pi1").setEntry("inputexpr", "niz", "niz");
    model.geom()
         .load(new String[]{"part2"}, "Ray_Optics_Module\\3D\\Mirrors\\spherical_mirror_3d.mph", new String[]{"part1"});
    model.component("comp1").geom("geom1").run("pi1");
    model.component("comp1").geom("geom1").create("pi2", "PartInstance");
    model.component("comp1").geom("geom1").feature("pi2").set("selkeepnoncontr", false);
    model.component("comp1").geom("geom1").feature("pi2").set("part", "part2");
    model.component("comp1").geom("geom1").feature("pi2").label("\u4e3b\u955c");
    model.component("comp1").geom("geom1").feature("pi2").setEntry("inputexpr", "R", "R_prim");
    model.component("comp1").geom("geom1").feature("pi2").setEntry("inputexpr", "Tc", "Tc_prim");
    model.component("comp1").geom("geom1").feature("pi2").setEntry("inputexpr", "d0", "d0_prim");
    model.component("comp1").geom("geom1").feature("pi2").setEntry("inputexpr", "d1", "d1_prim");
    model.component("comp1").geom("geom1").feature("pi2").setEntry("inputexpr", "d_clear", "dc_prim");
    model.component("comp1").geom("geom1").feature("pi2").setEntry("inputexpr", "d_hole", "dh_prim");
    model.component("comp1").geom("geom1").feature("pi2").setEntry("inputexpr", "nix", "0.0");
    model.component("comp1").geom("geom1").feature("pi2").setEntry("inputexpr", "niy", "0.0");
    model.component("comp1").geom("geom1").feature("pi2").setEntry("inputexpr", "niz", "1.0");
    model.component("comp1").geom("geom1").feature("pi2").set("workplanesrc", "pi1");
    model.component("comp1").geom("geom1").feature("pi2").set("workplane", "wp2");
    model.component("comp1").geom("geom1").feature("pi2").set("displ", new String[]{"0", "0", "z_prim"});
    model.geom()
         .load(new String[]{"part3"}, "Ray_Optics_Module\\3D\\Apertures_and_Obstructions\\circular_planar_annulus.mph", new String[]{"part1"});
    model.component("comp1").geom("geom1").run("pi2");
    model.component("comp1").geom("geom1").create("pi3", "PartInstance");
    model.component("comp1").geom("geom1").feature("pi3").set("selkeepnoncontr", false);
    model.component("comp1").geom("geom1").feature("pi3").set("part", "part3");
    model.component("comp1").geom("geom1").feature("pi3").label("\u50cf\u5e73\u9762");
    model.component("comp1").geom("geom1").feature("pi3").setEntry("inputexpr", "d0", "d0_img");
    model.component("comp1").geom("geom1").feature("pi3").setEntry("inputexpr", "d1", "0.0");
    model.component("comp1").geom("geom1").feature("pi3").set("workplanesrc", "pi2");
    model.component("comp1").geom("geom1").feature("pi3").set("workplane", "wp1");
    model.component("comp1").geom("geom1").feature("pi3").set("displ", new String[]{"0", "0", "z_img+delta_z_img"});
    model.component("comp1").geom("geom1").runPre("fin");
    model.component("comp1").geom("geom1").feature("pi1").setEntry("selkeepdom", "pi1_sel1", true);
    model.component("comp1").geom("geom1").feature("pi1").setEntry("selkeepbnd", "pi1_sel2", true);
    model.component("comp1").geom("geom1").feature("pi1").setEntry("selkeepbnd", "pi1_sel3", true);
    model.component("comp1").geom("geom1").selection().create("csel1", "CumulativeSelection");
    model.component("comp1").geom("geom1").selection("csel1").label("\u969c\u788d\u7269");
    model.component("comp1").geom("geom1").feature("pi1").setEntry("selcontributetobnd", "pi1_sel3", "csel1");
    model.component("comp1").geom("geom1").feature("pi1").setEntry("selcontributetobnd", "pi1_sel7", "csel1");
    model.component("comp1").geom("geom1").selection().create("csel2", "CumulativeSelection");
    model.component("comp1").geom("geom1").selection("csel2").label("\u53cd\u5c04\u955c");
    model.component("comp1").geom("geom1").feature("pi1").setEntry("selcontributetobnd", "pi1_sel4", "csel2");
    model.component("comp1").geom("geom1").selection().create("csel3", "CumulativeSelection");
    model.component("comp1").geom("geom1").selection("csel3").label("\u901a\u5149\u5b54\u5f84");
    model.component("comp1").geom("geom1").feature("pi1").setEntry("selcontributetobnd", "pi1_sel5", "csel3");
    model.component("comp1").geom("geom1").feature("pi1").setEntry("selcontributetobnd", "pi1_sel6", "csel3");
    model.component("comp1").geom("geom1").feature("pi2").setEntry("selcontributetobnd", "pi2_cylsel1", "csel2");
    model.component("comp1").geom("geom1").feature("pi2").setEntry("selcontributetobnd", "pi2_cylsel2", "csel1");
    model.component("comp1").geom("geom1").feature("pi2").setEntry("selkeepbnd", "pi2_comsel2", true);
    model.component("comp1").geom("geom1").feature("pi2").setEntry("selcontributetobnd", "pi2_comsel2", "csel1");
    model.component("comp1").geom("geom1").feature("pi3").setEntry("selkeepbnd", "pi3_sel1", true);

    model.title(null);

    model.description("");

    model.label("gregory_maksutov_telescope_geom_sequence.mph");

    return model;
  }

  public static void main(String[] args) {
    run();
  }

}
