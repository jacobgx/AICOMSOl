/*
 * cross_grating_echelle_spectrograph_geom_sequence.java
 */

import com.comsol.model.*;
import com.comsol.model.util.*;

/** Model exported on Apr 30 2026, 12:21 by COMSOL 6.3.0.290. */
public class cross_grating_echelle_spectrograph_geom_sequence {

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
    model.param().set("lam_mid", "525[nm]", "\u4e2d\u95f4\u6ce2\u957f");
    model.param().set("T_xdp", "500[1/mm]", "\u4ea4\u53c9\u8272\u6563\u7ebf\u5bc6\u5ea6");
    model.param().set("sigma_xdp", "1/T_xdp", "\u4ea4\u53c9\u8272\u6563\u7ebf\u95f4\u8ddd");
    model.param().set("theta_xdp", "asin(0.5*lam_mid/sigma_xdp)", "\u4ea4\u53c9\u8272\u6563\u5165\u5c04\u89d2");
    model.param().set("theta_B", "63.43[deg]", "\u9636\u68af\u5149\u6805\u95ea\u8000\u89d2");
    model.param().set("dtheta", "1.5[deg]", "\u9636\u68af\u5149\u6805\u9762\u5185\u89d2");
    model.param().set("gamma", "10.0[deg]", "\u9636\u68af\u5149\u6805\u9762\u5916\u89d2");
    model.param().set("R1_doub", "183.6850[mm]", "\u66f2\u7387\u534a\u5f84\uff0c\u8868\u9762 1");
    model.param().set("R2_doub", "43.2490[mm]", "\u66f2\u7387\u534a\u5f84\uff0c\u8868\u9762 2");
    model.param().set("R3_doub", "-64.1000[mm]", "\u66f2\u7387\u534a\u5f84\uff0c\u8868\u9762 3");
    model.param().set("Tc1_doub", "1.5[mm]", "\u4e2d\u5fc3\u539a\u5ea6\uff0c\u5143\u4ef6 1");
    model.param().set("Tc2_doub", "3.5[mm]", "\u4e2d\u5fc3\u539a\u5ea6\uff0c\u5143\u4ef6 2");
    model.param().set("d0_doub", "22.5[mm]", "\u900f\u955c\u76f4\u5f84");
    model.param().set("BFL_doub", "97.4495[mm]", "\u540e\u7126\u8ddd");

    model.component("comp1").geom("geom1").lengthUnit("mm");
    model.component("comp1").geom("geom1").create("pt1", "Point");
    model.component("comp1").geom("geom1").feature("pt1").label("\u5165\u5c04\u72ed\u7f1d\uff08\u70b9\uff09");
    model.component("comp1").geom("geom1").feature("pt1").set("selresult", true);
    model.geom()
         .load(new String[]{"part1"}, "Ray_Optics_Module\\3D\\Doublet_and_Triplet_Lenses\\spherical_doublet_lens_3d.mph", new String[]{"part3"});
    model.component("comp1").geom("geom1").run("pt1");
    model.component("comp1").geom("geom1").create("pi1", "PartInstance");
    model.component("comp1").geom("geom1").feature("pi1").set("selkeepnoncontr", false);
    model.component("comp1").geom("geom1").feature("pi1").set("part", "part1");
    model.component("comp1").geom("geom1").feature("pi1").label("\u51c6\u76f4\u900f\u955c");
    model.component("comp1").geom("geom1").feature("pi1").setEntry("inputexpr", "R1", "R1_doub");
    model.component("comp1").geom("geom1").feature("pi1").setEntry("inputexpr", "R2", "R2_doub");
    model.component("comp1").geom("geom1").feature("pi1").setEntry("inputexpr", "R3", "R3_doub");
    model.component("comp1").geom("geom1").feature("pi1").setEntry("inputexpr", "Tc1", "Tc1_doub");
    model.component("comp1").geom("geom1").feature("pi1").setEntry("inputexpr", "Tc2", "Tc2_doub");
    model.component("comp1").geom("geom1").feature("pi1").setEntry("inputexpr", "d0_1", "d0_doub");
    model.component("comp1").geom("geom1").feature("pi1").setEntry("inputexpr", "d0_2", "d0_doub");
    model.component("comp1").geom("geom1").feature("pi1").setEntry("inputexpr", "d1_clear", 0);
    model.component("comp1").geom("geom1").feature("pi1").setEntry("inputexpr", "d2_clear", 0);
    model.component("comp1").geom("geom1").feature("pi1").setEntry("inputexpr", "d3_clear", 0);
    model.component("comp1").geom("geom1").feature("pi1").set("displ", new String[]{"0", "0", "BFL_doub"});
    model.component("comp1").geom("geom1").feature("pi1").setEntry("selkeepdom", "pi1_sel2", true);
    model.component("comp1").geom("geom1").feature("pi1").setEntry("selkeepdom", "pi1_sel3", true);
    model.component("comp1").geom("geom1").run("pi1");
    model.component("comp1").geom("geom1").create("wp1", "WorkPlane");
    model.component("comp1").geom("geom1").feature("wp1").set("unite", true);
    model.component("comp1").geom("geom1").feature("wp1").label("\u4ea4\u53c9\u5149\u6805\u5165\u5c04\u53c2\u8003");
    model.component("comp1").geom("geom1").feature("wp1").set("planetype", "normalvector");
    model.component("comp1").geom("geom1").feature("wp1").set("normalvector", new int[]{0, 1, 0});
    model.component("comp1").geom("geom1").feature("wp1").set("normalcoord", new int[]{0, 0, 200});
    model.component("comp1").geom("geom1").run("wp1");
    model.component("comp1").geom("geom1").create("wp2", "WorkPlane");
    model.component("comp1").geom("geom1").feature("wp2").set("unite", true);
    model.component("comp1").geom("geom1").feature("wp2")
         .label("\u4ea4\u53c9\u5149\u6805\u76f8\u5207\u5c0f\u5e73\u9762");
    model.component("comp1").geom("geom1").feature("wp2").set("planetype", "transformed");
    model.component("comp1").geom("geom1").feature("wp2").set("workplane", "wp1");
    model.component("comp1").geom("geom1").feature("wp2").set("transrot", "theta_xdp-gamma");
    model.component("comp1").geom("geom1").run("wp2");
    model.component("comp1").geom("geom1").create("wp3", "WorkPlane");
    model.component("comp1").geom("geom1").feature("wp3").set("unite", true);
    model.component("comp1").geom("geom1").feature("wp3")
         .label("\u4ea4\u53c9\u5149\u6805\u6cd5\u5411\u5c0f\u5e73\u9762");
    model.component("comp1").geom("geom1").feature("wp3").set("planetype", "transformed");
    model.component("comp1").geom("geom1").feature("wp3").set("workplane", "wp2");
    model.component("comp1").geom("geom1").feature("wp3").set("transaxistype", "y");
    model.component("comp1").geom("geom1").feature("wp3").set("transrot", 90);
    model.component("comp1").geom("geom1").run("wp3");
    model.component("comp1").geom("geom1").create("wp4", "WorkPlane");
    model.component("comp1").geom("geom1").feature("wp4").set("unite", true);
    model.component("comp1").geom("geom1").feature("wp4").label("\u4ea4\u53c9\u5149\u6805\u8868\u9762");
    model.component("comp1").geom("geom1").feature("wp4").set("planetype", "transformed");
    model.component("comp1").geom("geom1").feature("wp4").set("workplane", "wp3");
    model.component("comp1").geom("geom1").feature("wp4").set("transaxistype", "y");
    model.component("comp1").geom("geom1").feature("wp4").set("transrot", "theta_B+dtheta");
    model.component("comp1").geom("geom1").feature("wp4").set("selresult", true);
    model.component("comp1").geom("geom1").feature("wp4").geom().create("r1", "Rectangle");
    model.component("comp1").geom("geom1").feature("wp4").geom().feature("r1")
         .set("size", new String[]{"50.0", "25.0"});
    model.component("comp1").geom("geom1").feature("wp4").geom().feature("r1").set("base", "center");
    model.component("comp1").geom("geom1").run("wp4");
    model.component("comp1").geom("geom1").feature().create("ext1", "Extrude");
    model.component("comp1").geom("geom1").feature("ext1").label("\u4ea4\u53c9\u5149\u6805");
    model.component("comp1").geom("geom1").feature("ext1").setIndex("distance", 10, 0);
    model.component("comp1").geom("geom1").feature("ext1").set("reverse", true);
    model.component("comp1").geom("geom1").run("ext1");
    model.component("comp1").geom("geom1").create("wp5", "WorkPlane");
    model.component("comp1").geom("geom1").feature("wp5").set("unite", true);
    model.component("comp1").geom("geom1").feature("wp5").label("\u4ea4\u53c9\u5149\u6805\u51fa\u5c04\u53c2\u8003");
    model.component("comp1").geom("geom1").feature("wp5").set("planetype", "transformed");
    model.component("comp1").geom("geom1").feature("wp5").set("workplane", "wp3");
    model.component("comp1").geom("geom1").feature("wp5").set("transaxistype", "x");
    model.component("comp1").geom("geom1").feature("wp5").set("transrot", "theta_xdp+gamma");
    model.component("comp1").geom("geom1")
         .insertFile("cross_grating_echelle_spectrograph_petzval_lens_geom_sequence.mph", "geom1");
    model.component("comp1").geom("geom1").feature("pi2").set("workplane", "wp5");
    model.component("comp1").geom("geom1").feature("pi2").set("displ", new String[]{"0", "-1.5[mm]", "75.0[mm]"});
    model.component("comp1").geom("geom1").feature("pi9").active(false);
    model.component("comp1").geom("geom1").feature("pi10").active(false);
    model.component("comp1").geom("geom1").feature("pi11").active(false);
    model.component("comp1").geom("geom1").run("pi11");
    model.component("comp1").geom("geom1").create("sca1", "Scale");
    model.component("comp1").geom("geom1").feature("sca1").selection("input")
         .set("pi2", "pi3", "pi4", "pi5", "pi6", "pi7", "pi8");
    model.component("comp1").geom("geom1").feature("sca1").set("isotropic", 0.667);
    model.component("comp1").geom("geom1").feature("sca1").set("workplanesrc", "pi2");
    model.component("comp1").geom("geom1").feature("sca1").set("workplane", "wp1");
    model.component("comp1").geom("geom1").feature("ext1").set("contributeto", "csel1");

    model.param().set("d0_D", "25.0[mm]");

    model.component("comp1").geom("geom1").runPre("fin");

    model.title(null);

    model.description("");

    model.label("cross_grating_echelle_spectrograph_geom_sequence.mph");

    return model;
  }

  public static void main(String[] args) {
    run();
  }

}
