/*
 * keck_telescope.java
 */

import com.comsol.model.*;
import com.comsol.model.util.*;

/** Model exported on Apr 30 2026, 12:17 by COMSOL 6.3.0.290. */
public class keck_telescope {

  public static Model run() {
    Model model = ModelUtil.create("Model");

    model
         .modelPath("D:\\Program Files\\COMSOL\\COMSOL63\\Multiphysics\\applications\\Ray_Optics_Module\\Lenses_Cameras_and_Telescopes");

    model.component().create("comp1", true);

    model.component("comp1").geom().create("geom1", 3);
    model.component("comp1").geom("geom1").geomRep("comsol");

    model.component("comp1").mesh().create("mesh1");
    model.component("comp1").mesh("mesh1").contribute("geom/detail", true);

    model.component("comp1").physics().create("gop", "GeometricalOptics", "geom1");

    model.study().create("std1");
    model.study("std1").create("rtrac", "RayTracing");
    model.study("std1").feature("rtrac").setSolveFor("/physics/gop", true);

//    To import content from file, use:
//    model.param().loadFile("FILENAME");
    model.param().set("nix", "0.0", "\u5168\u5c40\u5149\u8f74\uff0cx \u5206\u91cf");
    model.param().set("niy", "0.0", "\u5168\u5c40\u5149\u8f74\uff0cy \u5206\u91cf");
    model.param().set("niz", "-1.0", "\u5168\u5c40\u5149\u8f74\uff0cz \u5206\u91cf");
    model.param().set("lam_vac", "550[nm]", "\u771f\u7a7a\u6ce2\u957f");
    model.param().set("theta_x1", "0.0[arcmin]", "\u89c6\u573a\u89d2 1\uff0cx \u5206\u91cf");
    model.param().set("theta_y1", "0.0[arcmin]", "\u89c6\u573a\u89d2 1\uff0cy \u5206\u91cf");
    model.param().set("theta_x2", "2.5[arcmin]", "\u89c6\u573a\u89d2 2\uff0cx \u5206\u91cf");
    model.param().set("theta_y2", "0.0[arcmin]", "\u89c6\u573a\u89d2 2\uff0cy \u5206\u91cf");
    model.param().set("theta_x3", "5.0[arcmin]", "\u89c6\u573a\u89d2 3\uff0cx \u5206\u91cf");
    model.param().set("theta_y3", "0.0[arcmin]", "\u89c6\u573a\u89d2 3\uff0cy \u5206\u91cf");
    model.param().set("N_ring", "18", "\u516d\u8fb9\u73af\u6570");
    model.param().set("P_nom", "10.949[m]", "\u6807\u79f0\u5165\u5c04\u5149\u77b3\u76f4\u5f84");
    model.param()
         .set("R_nom", "0.995*P_nom/2", "\u6807\u79f0\u5165\u5c04\u5149\u77b3\u534a\u5f84\uff08\u5df2\u7f29\u653e\uff09");
    model.param().set("F_nom", "15.0", "\u6807\u79f0\u7126\u6bd4");
    model.param().set("f_nom", "F_nom*P_nom", "\u6807\u79f0\u7126\u8ddd");
    model.param().set("R_prim", "-35.00000[m]", "\u53cd\u5c04\u955c\u66f2\u7387\u534a\u5f84");
    model.param().set("k_prim", "-1.0037963", "\u5706\u9525\u5e38\u6570");
    model.param().set("Tc_prim", "75.0[mm]", "\u955c\u50cf\u539a\u5ea6");
    model.param().set("d0_prim", "1.79[m]", "\u53cd\u5c04\u955c\u6bb5\u76f4\u5f84");
    model.param().set("mtype", "1", "\u955c\u9762\u79bb\u8f74\u7c7b\u578b\uff08\u72ec\u7acb\uff09");
    model.param().set("nside", "6", "\u591a\u8fb9\u5f62\u7684\u8fb9\u6570");
    model.param().set("r1", "1.55885[m]", "\u53cd\u5c04\u955c 1 \u5f84\u5411\u5750\u6807");
    model.param().set("rho1", "0", "\u53cd\u5c04\u955c 1 \u6781\u89d2");
    model.param().set("phi1", "30[deg]", "\u53cd\u5c04\u955c 1 \u65cb\u8f6c\u89d2\u5ea6");
    model.param().set("r2", "2.70000[m]", "\u53cd\u5c04\u955c 2 \u5f84\u5411\u5750\u6807");
    model.param().set("rho2", "30[deg]", "\u53cd\u5c04\u955c 2 \u6781\u89d2");
    model.param().set("phi2", "0", "\u53cd\u5c04\u955c 2 \u65cb\u8f6c\u89d2\u5ea6");
    model.param().set("r3", "3.11769[m]", "\u53cd\u5c04\u955c 3 \u5f84\u5411\u5750\u6807");
    model.param().set("rho3", "0", "\u53cd\u5c04\u955c 3 \u6781\u89d2");
    model.param().set("phi3", "30[deg]", "\u53cd\u5c04\u955c 3 \u65cb\u8f6c\u89d2\u5ea6");
    model.param()
         .set("rho45", "atan(sqrt(3)/5)", "\u53cd\u5c04\u955c 4 \u548c 5 \u6781\u89d2\uff08\u7edd\u5bf9\uff09");
    model.param().set("r4", "4.12432[m]", "\u53cd\u5c04\u955c 4 \u5f84\u5411\u5750\u6807");
    model.param().set("rho4", "rho45", "\u53cd\u5c04\u955c 4 \u6781\u89d2");
    model.param().set("phi4", "rho45-30[deg]", "\u53cd\u5c04\u955c 4 \u65cb\u8f6c\u89d2\u5ea6");
    model.param().set("r5", "4.12432[m]", "\u53cd\u5c04\u955c 5 \u5f84\u5411\u5750\u6807");
    model.param().set("rho5", "-rho45", "\u53cd\u5c04\u955c 5 \u6781\u89d2");
    model.param().set("phi5", "30[deg]-rho45", "\u53cd\u5c04\u955c 5 \u65cb\u8f6c\u89d2\u5ea6");
    model.param().set("r6", "4.67654[m]", "\u53cd\u5c04\u955c 6 \u5f84\u5411\u5750\u6807");
    model.param().set("rho6", "0", "\u53cd\u5c04\u955c 6 \u6781\u89d2");
    model.param().set("phi6", "30[deg]", "\u53cd\u5c04\u955c 6 \u65cb\u8f6c\u89d2\u5ea6");
    model.param().set("R_sec", "4.849338[m]", "\u526f\u955c\u66f2\u7387\u534a\u5f84");
    model.param().set("k_sec", "-1.6430812", "\u526f\u955c\u5706\u9525\u5e38\u6570");
    model.param().set("Tc_sec", "150.0[mm]", "\u526f\u955c\u539a\u5ea6");
    model.param().set("d0_sec", "1.429[m]", "\u526f\u955c\u76f4\u5f84");
    model.param()
         .set("Z_sec", "-15.35821[m]", "\u526f\u955c z \u4f4d\u7f6e\uff08\u76f8\u5bf9\u4e8e\u4e3b\u955c\u9876\u70b9\uff09");
    model.param().set("d0_ter", "1.04[m]", "\u4e09\u955c\u77ed\u8f74\u76f4\u5f84");
    model.param().set("theta_ter", "45.0[deg]", "\u4e09\u955c\u6298\u89d2");
    model.param()
         .set("Z_ter", "-4.0[m]", "\u4e09\u955c z \u4f4d\u7f6e\uff08\u76f8\u5bf9\u4e8e\u4e3b\u955c\u9876\u70b9\uff09");
    model.param().set("d_img", "0.873[m]", "\u50cf\u9762\u76f4\u5f84");
    model.param().set("C_img", "0.471[1/m]", "\u50cf\u9762\u66f2\u7387");
    model.param()
         .set("Z_img", "-7.0[m]", "\u50cf\u9762 z \u4f4d\u7f6e\uff08\u76f8\u5bf9\u4e8e\u4e09\u955c\u51fa\u5c04\u9762\uff09");
    model.param().set("vz", "1", "\u5149\u7ebf\u65b9\u5411\u77e2\u91cf\uff0cz \u5206\u91cf");
    model.param().set("vx1", "tan(theta_x1)", "\u5149\u7ebf\u65b9\u5411\u77e2\u91cf\uff0cx \u5206\u91cf");
    model.param().set("vy1", "tan(theta_y1)", "\u5149\u7ebf\u65b9\u5411\u77e2\u91cf\uff0cy \u5206\u91cf");
    model.param().set("vx2", "tan(theta_x2)", "\u5149\u7ebf\u65b9\u5411\u77e2\u91cf\uff0cx \u5206\u91cf");
    model.param().set("vy2", "tan(theta_y2)", "\u5149\u7ebf\u65b9\u5411\u77e2\u91cf\uff0cy \u5206\u91cf");
    model.param().set("vx3", "tan(theta_x3)", "\u5149\u7ebf\u65b9\u5411\u77e2\u91cf\uff0cx \u5206\u91cf");
    model.param().set("vy3", "tan(theta_y3)", "\u5149\u7ebf\u65b9\u5411\u77e2\u91cf\uff0cy \u5206\u91cf");
    model.param().set("dz", "16.0[m]", "\u5149\u7ebf\u53d1\u5c04 z \u5750\u6807");
    model.param().set("dx1", "dz*tan(theta_x1)", "\u5149\u7ebf\u53d1\u5c04\uff0c\u89c6\u573a 1\uff0cx \u5750\u6807");
    model.param().set("dy1", "dz*tan(theta_y1)", "\u5149\u7ebf\u53d1\u5c04\uff0c\u89c6\u573a 1\uff0cy \u5750\u6807");
    model.param().set("dx2", "dz*tan(theta_x2)", "\u5149\u7ebf\u53d1\u5c04\uff0c\u89c6\u573a 2\uff0cx \u5750\u6807");
    model.param().set("dy2", "dz*tan(theta_y2)", "\u5149\u7ebf\u53d1\u5c04\uff0c\u89c6\u573a 2\uff0cy \u5750\u6807");
    model.param().set("dx3", "dz*tan(theta_x3)", "\u5149\u7ebf\u53d1\u5c04\uff0c\u89c6\u573a 3\uff0cx \u5750\u6807");
    model.param().set("dy3", "dz*tan(theta_y3)", "\u5149\u7ebf\u53d1\u5c04\uff0c\u89c6\u573a 3\uff0cy \u5750\u6807");
    model.param().set("theta_Airy", "1.22*lam_vac/P_nom", "\u827e\u91cc\u6591\u89d2\u534a\u5f84");
    model.param().set("r_Airy", "f_nom*theta_Airy", "\u827e\u91cc\u6591\u534a\u5f84");

    model.geom()
         .load(new String[]{"part1"}, "Ray_Optics_Module\\3D\\Mirrors\\conic_polygonal_mirror_off_axis_3d.mph", new String[]{"part1"});
    model.component("comp1").geom("geom1").create("pi1", "PartInstance");
    model.component("comp1").geom("geom1").feature("pi1").set("selkeepnoncontr", false);
    model.component("comp1").geom("geom1").feature("pi1").set("part", "part1");
    model.component("comp1").geom("geom1").feature("pi1").label("\u4e3b\u955c 1");
    model.component("comp1").geom("geom1").feature("pi1").setEntry("inputexpr", "R", "R_prim");
    model.component("comp1").geom("geom1").feature("pi1").setEntry("inputexpr", "k", "k_prim");
    model.component("comp1").geom("geom1").feature("pi1").setEntry("inputexpr", "Tc", "Tc_prim");
    model.component("comp1").geom("geom1").feature("pi1").setEntry("inputexpr", "d0", "d0_prim");
    model.component("comp1").geom("geom1").feature("pi1").setEntry("inputexpr", "d_clear", 0);
    model.component("comp1").geom("geom1").feature("pi1").setEntry("inputexpr", "dx", "r1");
    model.component("comp1").geom("geom1").feature("pi1").setEntry("inputexpr", "nix", "0.0");
    model.component("comp1").geom("geom1").feature("pi1").setEntry("inputexpr", "niy", "0.0");
    model.component("comp1").geom("geom1").feature("pi1").setEntry("inputexpr", "niz", "-1.0");
    model.component("comp1").geom("geom1").feature("pi1").setEntry("inputexpr", "nxx", "0.0");
    model.component("comp1").geom("geom1").feature("pi1").setEntry("inputexpr", "nxy", "1.0");
    model.component("comp1").geom("geom1").feature("pi1").setEntry("inputexpr", "nxz", "0.0");
    model.component("comp1").geom("geom1").feature("pi1").setEntry("inputexpr", "n_side", "nside");
    model.component("comp1").geom("geom1").feature("pi1").setEntry("inputexpr", "phi_rot", "phi1");
    model.component("comp1").geom("geom1").feature("pi1").setEntry("inputexpr", "mtype", "mtype");
    model.component("comp1").geom("geom1").feature("pi1").set("rot", "rho1");
    model.component("comp1").geom("geom1").selection().create("csel1", "CumulativeSelection");
    model.component("comp1").geom("geom1").selection("csel1").label("\u53cd\u5c04\u955c");
    model.component("comp1").geom("geom1").selection().create("csel2", "CumulativeSelection");
    model.component("comp1").geom("geom1").selection("csel2").label("\u969c\u788d\u7269");
    model.component("comp1").geom("geom1").feature("pi1").setEntry("selcontributetobnd", "pi1_sel3", "csel1");
    model.component("comp1").geom("geom1").feature("pi1").setEntry("selcontributetobnd", "pi1_sel4", "csel2");
    model.component("comp1").geom("geom1").feature("pi1").setEntry("selcontributetobnd", "pi1_sel5", "csel2");
    model.component("comp1").geom("geom1").feature("pi1").setEntry("selcontributetobnd", "pi1_sel6", "csel2");
    model.component("comp1").geom("geom1").feature().duplicate("pi2", "pi1");
    model.component("comp1").geom("geom1").feature("pi1").set("selcontributetoobj", new String[]{});
    model.component("comp1").geom("geom1").feature("pi1").set("selkeepobj", new String[]{});
    model.component("comp1").geom("geom1").feature("pi1").set("selcontributetopnt", new String[]{"none"});
    model.component("comp1").geom("geom1").feature("pi1").set("selkeeppnt", new String[]{"off"});
    model.component("comp1").geom("geom1").feature("pi1").set("selshowpnt", new String[]{"on"});
    model.component("comp1").geom("geom1").feature("pi1").set("selcontributetoedg", new String[]{});
    model.component("comp1").geom("geom1").feature("pi1").set("selkeepedg", new String[]{});
    model.component("comp1").geom("geom1").feature("pi1").set("selshowedg", new String[]{});
    model.component("comp1").geom("geom1").feature("pi1")
         .set("selcontributetobnd", new String[]{"none", "csel1", "csel2", "csel2", "csel2"});
    model.component("comp1").geom("geom1").feature("pi1")
         .set("selkeepbnd", new String[]{"off", "off", "off", "off", "off"});
    model.component("comp1").geom("geom1").feature("pi1")
         .set("selshowbnd", new String[]{"on", "on", "on", "on", "on"});
    model.component("comp1").geom("geom1").feature("pi1").set("selcontributetodom", new String[]{"none"});
    model.component("comp1").geom("geom1").feature("pi1").set("selkeepdom", new String[]{"off"});
    model.component("comp1").geom("geom1").feature("pi1").set("selshowdom", new String[]{"on"});
    model.component("comp1").geom("geom1").feature("pi2").label("\u4e3b\u955c 2");
    model.component("comp1").geom("geom1").feature("pi2").setEntry("inputexpr", "dx", "r2");
    model.component("comp1").geom("geom1").feature("pi2").setEntry("inputexpr", "phi_rot", "phi2");
    model.component("comp1").geom("geom1").feature("pi2").set("rot", "rho2");
    model.component("comp1").geom("geom1").feature().duplicate("pi3", "pi2");
    model.component("comp1").geom("geom1").feature("pi2").set("selcontributetoobj", new String[]{});
    model.component("comp1").geom("geom1").feature("pi2").set("selkeepobj", new String[]{});
    model.component("comp1").geom("geom1").feature("pi2").set("selcontributetopnt", new String[]{"none"});
    model.component("comp1").geom("geom1").feature("pi2").set("selkeeppnt", new String[]{"off"});
    model.component("comp1").geom("geom1").feature("pi2").set("selshowpnt", new String[]{"on"});
    model.component("comp1").geom("geom1").feature("pi2").set("selcontributetoedg", new String[]{});
    model.component("comp1").geom("geom1").feature("pi2").set("selkeepedg", new String[]{});
    model.component("comp1").geom("geom1").feature("pi2").set("selshowedg", new String[]{});
    model.component("comp1").geom("geom1").feature("pi2")
         .set("selcontributetobnd", new String[]{"none", "csel1", "csel2", "csel2", "csel2"});
    model.component("comp1").geom("geom1").feature("pi2")
         .set("selkeepbnd", new String[]{"off", "off", "off", "off", "off"});
    model.component("comp1").geom("geom1").feature("pi2")
         .set("selshowbnd", new String[]{"on", "on", "on", "on", "on"});
    model.component("comp1").geom("geom1").feature("pi2").set("selcontributetodom", new String[]{"none"});
    model.component("comp1").geom("geom1").feature("pi2").set("selkeepdom", new String[]{"off"});
    model.component("comp1").geom("geom1").feature("pi2").set("selshowdom", new String[]{"on"});
    model.component("comp1").geom("geom1").feature("pi3").label("\u4e3b\u955c 3");
    model.component("comp1").geom("geom1").feature("pi3").setEntry("inputexpr", "dx", "r3");
    model.component("comp1").geom("geom1").feature("pi3").setEntry("inputexpr", "phi_rot", "phi3");
    model.component("comp1").geom("geom1").feature("pi3").set("rot", "rho3");
    model.component("comp1").geom("geom1").feature().duplicate("pi4", "pi3");
    model.component("comp1").geom("geom1").feature("pi3").set("selcontributetoobj", new String[]{});
    model.component("comp1").geom("geom1").feature("pi3").set("selkeepobj", new String[]{});
    model.component("comp1").geom("geom1").feature("pi3").set("selcontributetopnt", new String[]{"none"});
    model.component("comp1").geom("geom1").feature("pi3").set("selkeeppnt", new String[]{"off"});
    model.component("comp1").geom("geom1").feature("pi3").set("selshowpnt", new String[]{"on"});
    model.component("comp1").geom("geom1").feature("pi3").set("selcontributetoedg", new String[]{});
    model.component("comp1").geom("geom1").feature("pi3").set("selkeepedg", new String[]{});
    model.component("comp1").geom("geom1").feature("pi3").set("selshowedg", new String[]{});
    model.component("comp1").geom("geom1").feature("pi3")
         .set("selcontributetobnd", new String[]{"none", "csel1", "csel2", "csel2", "csel2"});
    model.component("comp1").geom("geom1").feature("pi3")
         .set("selkeepbnd", new String[]{"off", "off", "off", "off", "off"});
    model.component("comp1").geom("geom1").feature("pi3")
         .set("selshowbnd", new String[]{"on", "on", "on", "on", "on"});
    model.component("comp1").geom("geom1").feature("pi3").set("selcontributetodom", new String[]{"none"});
    model.component("comp1").geom("geom1").feature("pi3").set("selkeepdom", new String[]{"off"});
    model.component("comp1").geom("geom1").feature("pi3").set("selshowdom", new String[]{"on"});
    model.component("comp1").geom("geom1").feature("pi4").label("\u4e3b\u955c 4");
    model.component("comp1").geom("geom1").feature("pi4").setEntry("inputexpr", "dx", "r4");
    model.component("comp1").geom("geom1").feature("pi4").setEntry("inputexpr", "phi_rot", "phi4");
    model.component("comp1").geom("geom1").feature("pi4").set("rot", "rho4");
    model.component("comp1").geom("geom1").feature().duplicate("pi5", "pi4");
    model.component("comp1").geom("geom1").feature("pi4").set("selcontributetoobj", new String[]{});
    model.component("comp1").geom("geom1").feature("pi4").set("selkeepobj", new String[]{});
    model.component("comp1").geom("geom1").feature("pi4").set("selcontributetopnt", new String[]{"none"});
    model.component("comp1").geom("geom1").feature("pi4").set("selkeeppnt", new String[]{"off"});
    model.component("comp1").geom("geom1").feature("pi4").set("selshowpnt", new String[]{"on"});
    model.component("comp1").geom("geom1").feature("pi4").set("selcontributetoedg", new String[]{});
    model.component("comp1").geom("geom1").feature("pi4").set("selkeepedg", new String[]{});
    model.component("comp1").geom("geom1").feature("pi4").set("selshowedg", new String[]{});
    model.component("comp1").geom("geom1").feature("pi4")
         .set("selcontributetobnd", new String[]{"none", "csel1", "csel2", "csel2", "csel2"});
    model.component("comp1").geom("geom1").feature("pi4")
         .set("selkeepbnd", new String[]{"off", "off", "off", "off", "off"});
    model.component("comp1").geom("geom1").feature("pi4")
         .set("selshowbnd", new String[]{"on", "on", "on", "on", "on"});
    model.component("comp1").geom("geom1").feature("pi4").set("selcontributetodom", new String[]{"none"});
    model.component("comp1").geom("geom1").feature("pi4").set("selkeepdom", new String[]{"off"});
    model.component("comp1").geom("geom1").feature("pi4").set("selshowdom", new String[]{"on"});
    model.component("comp1").geom("geom1").feature("pi5").label("\u4e3b\u955c 5");
    model.component("comp1").geom("geom1").feature("pi5").setEntry("inputexpr", "dx", "r5");
    model.component("comp1").geom("geom1").feature("pi5").setEntry("inputexpr", "phi_rot", "phi5");
    model.component("comp1").geom("geom1").feature("pi5").set("rot", "rho5");
    model.component("comp1").geom("geom1").feature().duplicate("pi6", "pi5");
    model.component("comp1").geom("geom1").feature("pi5").set("selcontributetoobj", new String[]{});
    model.component("comp1").geom("geom1").feature("pi5").set("selkeepobj", new String[]{});
    model.component("comp1").geom("geom1").feature("pi5").set("selcontributetopnt", new String[]{"none"});
    model.component("comp1").geom("geom1").feature("pi5").set("selkeeppnt", new String[]{"off"});
    model.component("comp1").geom("geom1").feature("pi5").set("selshowpnt", new String[]{"on"});
    model.component("comp1").geom("geom1").feature("pi5").set("selcontributetoedg", new String[]{});
    model.component("comp1").geom("geom1").feature("pi5").set("selkeepedg", new String[]{});
    model.component("comp1").geom("geom1").feature("pi5").set("selshowedg", new String[]{});
    model.component("comp1").geom("geom1").feature("pi5")
         .set("selcontributetobnd", new String[]{"none", "csel1", "csel2", "csel2", "csel2"});
    model.component("comp1").geom("geom1").feature("pi5")
         .set("selkeepbnd", new String[]{"off", "off", "off", "off", "off"});
    model.component("comp1").geom("geom1").feature("pi5")
         .set("selshowbnd", new String[]{"on", "on", "on", "on", "on"});
    model.component("comp1").geom("geom1").feature("pi5").set("selcontributetodom", new String[]{"none"});
    model.component("comp1").geom("geom1").feature("pi5").set("selkeepdom", new String[]{"off"});
    model.component("comp1").geom("geom1").feature("pi5").set("selshowdom", new String[]{"on"});
    model.component("comp1").geom("geom1").feature("pi6").label("\u4e3b\u955c 6");
    model.component("comp1").geom("geom1").feature("pi6").setEntry("inputexpr", "dx", "r6");
    model.component("comp1").geom("geom1").feature("pi6").setEntry("inputexpr", "phi_rot", "phi6");
    model.component("comp1").geom("geom1").feature("pi6").set("rot", "rho6");
    model.component("comp1").geom("geom1").run("pi6");
    model.component("comp1").geom("geom1").create("rot1", "Rotate");
    model.component("comp1").geom("geom1").feature("rot1").selection("input")
         .set("pi1", "pi2", "pi3", "pi4", "pi5", "pi6");
    model.component("comp1").geom("geom1").feature("rot1").set("rot", "range(0,60,300)");
    model.geom()
         .load(new String[]{"part2"}, "Ray_Optics_Module\\3D\\Mirrors\\conic_mirror_on_axis_3d.mph", new String[]{"part1"});
    model.component("comp1").geom("geom1").run("rot1");
    model.component("comp1").geom("geom1").create("pi7", "PartInstance");
    model.component("comp1").geom("geom1").feature("pi7").set("selkeepnoncontr", false);
    model.component("comp1").geom("geom1").feature("pi7").set("part", "part2");
    model.component("comp1").geom("geom1").feature("pi7").label("\u526f\u955c");
    model.component("comp1").geom("geom1").feature("pi7").setEntry("inputexpr", "R", "R_sec");
    model.component("comp1").geom("geom1").feature("pi7").setEntry("inputexpr", "k", "k_sec");
    model.component("comp1").geom("geom1").feature("pi7").setEntry("inputexpr", "Tc", "Tc_sec");
    model.component("comp1").geom("geom1").feature("pi7").setEntry("inputexpr", "d0", "d0_sec");
    model.component("comp1").geom("geom1").feature("pi7").setEntry("inputexpr", "d1", 0);
    model.component("comp1").geom("geom1").feature("pi7").setEntry("inputexpr", "d_clear", 0);
    model.component("comp1").geom("geom1").feature("pi7").setEntry("inputexpr", "d_hole", 0);
    model.component("comp1").geom("geom1").feature("pi7").setEntry("inputexpr", "nix", "0.0");
    model.component("comp1").geom("geom1").feature("pi7").setEntry("inputexpr", "niy", "0.0");
    model.component("comp1").geom("geom1").feature("pi7").setEntry("inputexpr", "niz", "-1.0");
    model.component("comp1").geom("geom1").feature("pi7").setEntry("inputexpr", "n_extra_a", 30);
    model.component("comp1").geom("geom1").feature("pi7").set("workplanesrc", "pi1");
    model.component("comp1").geom("geom1").feature("pi7").set("workplane", "wp1");
    model.component("comp1").geom("geom1").feature("pi7").set("displ", new String[]{"0", "0", "Z_sec"});
    model.component("comp1").geom("geom1").feature("pi7").setEntry("selkeepbnd", "pi7_cylsel1", true);
    model.component("comp1").geom("geom1").feature("pi7").setEntry("selcontributetobnd", "pi7_cylsel1", "csel1");
    model.component("comp1").geom("geom1").feature("pi7").setEntry("selcontributetobnd", "pi7_adjsel1", "csel2");
    model.component("comp1").geom("geom1").feature("pi7").setEntry("selcontributetobnd", "pi7_cylsel2", "csel2");
    model.component("comp1").geom("geom1").feature("pi7").setEntry("selcontributetobnd", "pi7_comsel2", "csel2");
    model.geom()
         .load(new String[]{"part3"}, "Ray_Optics_Module\\3D\\Mirrors\\elliptical_planar_mirror_3d.mph", new String[]{"part1"});
    model.component("comp1").geom("geom1").run("pi7");
    model.component("comp1").geom("geom1").create("pi8", "PartInstance");
    model.component("comp1").geom("geom1").feature("pi8").set("selkeepnoncontr", false);
    model.component("comp1").geom("geom1").feature("pi8").set("part", "part3");
    model.component("comp1").geom("geom1").feature("pi8").label("\u4e09\u955c");
    model.component("comp1").geom("geom1").feature("pi8").setEntry("inputexpr", "Tc", "75.0[mm]");
    model.component("comp1").geom("geom1").feature("pi8").setEntry("inputexpr", "d0", "d0_ter");
    model.component("comp1").geom("geom1").feature("pi8").setEntry("inputexpr", "dx", 0);
    model.component("comp1").geom("geom1").feature("pi8").setEntry("inputexpr", "niz", "1.0");
    model.component("comp1").geom("geom1").feature("pi8").setEntry("inputexpr", "nxx", "1.0");
    model.component("comp1").geom("geom1").feature("pi8").setEntry("inputexpr", "nxy", "0.0");
    model.component("comp1").geom("geom1").feature("pi8").setEntry("inputexpr", "nxz", "0.0");
    model.component("comp1").geom("geom1").feature("pi8").set("workplanesrc", "pi1");
    model.component("comp1").geom("geom1").feature("pi8").set("workplane", "wp1");
    model.component("comp1").geom("geom1").feature("pi8").set("displ", new String[]{"0", "0", "Z_ter"});
    model.component("comp1").geom("geom1").feature("pi8").setEntry("selkeepbnd", "pi8_cylsel1", true);
    model.component("comp1").geom("geom1").feature("pi8").setEntry("selcontributetobnd", "pi8_cylsel1", "csel1");
    model.component("comp1").geom("geom1").feature("pi8").setEntry("selcontributetobnd", "pi8_adjsel1", "csel2");
    model.component("comp1").geom("geom1").feature("pi8").setEntry("selcontributetobnd", "pi8_comsel1", "csel2");
    model.component("comp1").geom("geom1").run("pi8");
    model.component("comp1").geom("geom1").create("ps1", "ParametricSurface");
    model.component("comp1").geom("geom1").feature("ps1").label("\u50cf\u9762");
    model.component("comp1").geom("geom1").feature("ps1").set("parmin1", "-d_img/2");
    model.component("comp1").geom("geom1").feature("ps1").set("parmax1", "d_img/2");
    model.component("comp1").geom("geom1").feature("ps1").set("parmin2", "-d_img/2");
    model.component("comp1").geom("geom1").feature("ps1").set("parmax2", "d_img/2");
    model.component("comp1").geom("geom1").feature("ps1")
         .set("coord", new String[]{"s1", "s2", "C_img*(s1^2 + s2^2)/(1 + sqrt(1 - C_img^2*(s1^2 + s2^2)))*1[m]"});
    model.component("comp1").geom("geom1").feature("ps1").set("workplanesrc", "pi8");
    model.component("comp1").geom("geom1").feature("ps1").set("workplane", "wp4");
    model.component("comp1").geom("geom1").feature("ps1").set("pos", new String[]{"0", "0", "Z_img"});
    model.component("comp1").geom("geom1").feature("ps1").set("selresult", true);
    model.component("comp1").geom("geom1").run("fin");

    model.component("comp1").physics("gop").selection().set();
    model.component("comp1").physics("gop").prop("MaximumSecondary").setIndex("MaximumSecondary", 0, 0);
    model.component("comp1").physics("gop").prop("CountReflections").setIndex("CountReflections", 1, 0);
    model.component("comp1").physics("gop").create("mir1", "Mirror", 2);
    model.component("comp1").physics("gop").feature("mir1").label("\u53cd\u5c04\u955c");
    model.component("comp1").physics("gop").feature("mir1").selection().named("geom1_csel1_bnd");
    model.component("comp1").physics("gop").create("wall1", "Wall", 2);
    model.component("comp1").physics("gop").feature("wall1").label("\u969c\u788d\u7269");
    model.component("comp1").physics("gop").feature("wall1").selection().named("geom1_csel2_bnd");
    model.component("comp1").physics("gop").feature("wall1").set("WallCondition", "Disappear");
    model.component("comp1").physics("gop").create("wall2", "Wall", 2);
    model.component("comp1").physics("gop").feature("wall2").label("\u50cf");
    model.component("comp1").physics("gop").feature("wall2").selection().named("geom1_ps1_bnd");
    model.component("comp1").physics("gop").create("relg1", "ReleaseGrid", -1);
    model.component("comp1").physics("gop").feature("relg1").set("GridType", "Hexapolar");
    model.component("comp1").physics("gop").feature("relg1").set("qcc", new String[]{"-dx1", "-dy1", "dz"});
    model.component("comp1").physics("gop").feature("relg1").set("rcc", new int[]{0, 0, 1});
    model.component("comp1").physics("gop").feature("relg1").set("Rc", "R_nom");
    model.component("comp1").physics("gop").feature("relg1").setIndex("Ncr", "N_ring", 0);
    model.component("comp1").physics("gop").feature("relg1").set("L0", new String[]{"vx1", "vy1", "-vz"});
    model.component("comp1").physics("gop").feature().duplicate("relg2", "relg1");
    model.component("comp1").physics("gop").feature("relg2").set("qcc", new String[]{"-dx2", "-dy2", "dz"});
    model.component("comp1").physics("gop").feature("relg2").set("L0", new String[]{"vx2", "vy2", "-vz"});
    model.component("comp1").physics("gop").feature().duplicate("relg3", "relg2");
    model.component("comp1").physics("gop").feature("relg3").set("qcc", new String[]{"-dx3", "-dy3", "dz"});
    model.component("comp1").physics("gop").feature("relg3").set("L0", new String[]{"vx3", "vy3", "-vz"});
    model.component("comp1").physics("gop").create("rt1", "RayTermination", -1);
    model.component("comp1").physics("gop").feature("rt1")
         .set("SpatialExtentsOfRayPropagation", "BoundingBoxFromGeometry");

    model.component("comp1").mesh("mesh1").autoMeshSize(8);
    model.component("comp1").mesh("mesh1").run();

    model.study("std1").feature("rtrac").set("timestepspec", "specifylength");
    model.study("std1").feature("rtrac").set("llist", "0 60");
    model.study("std1").createAutoSequences("all");

    model.sol("sol1").runAll();

    model.result().dataset().create("ray1", "Ray");
    model.result().dataset("ray1").set("solution", "sol1");
    model.result().dataset("ray1").set("posdof", new String[]{"comp1.qx", "comp1.qy", "comp1.qz"});
    model.result().dataset("ray1").set("geom", "geom1");
    model.result().dataset("ray1").set("rgeom", "pgeom_gop");
    model.result().dataset("ray1").set("rgeomspec", "fromphysics");
    model.result().dataset("ray1").set("physicsinterface", "gop");
    model.result().create("pg1", "PlotGroup3D");
    model.result("pg1").set("data", "ray1");
    model.result("pg1").setIndex("looplevel", 2, 0);
    model.result("pg1").label("\u5c04\u7ebf\u8f68\u8ff9 (gop)");
    model.result("pg1").create("rtrj1", "RayTrajectories");
    model.result("pg1").feature("rtrj1").set("linetype", "line");
    model.result("pg1").feature("rtrj1").create("col1", "Color");
    model.result("pg1").feature("rtrj1").feature("col1").set("expr", "t");
    model.result("pg1").feature("rtrj1").create("filt1", "RayTrajectoriesFilter");
    model.result("pg1").run();
    model.result("pg1").label("\u5c04\u7ebf\u56fe");
    model.result("pg1").set("showlegendsunit", true);
    model.result("pg1").run();
    model.result("pg1").feature("rtrj1").feature("col1").set("expr", "at('last',gop.rrel)");
    model.result("pg1").feature("rtrj1").feature("col1").set("unit", "\u00b5m");
    model.result("pg1").run();
    model.result("pg1").create("surf1", "Surface");
    model.result("pg1").feature("surf1").set("expr", "x^2+y^2");
    model.result("pg1").feature("surf1").set("coloring", "gradient");
    model.result("pg1").feature("surf1").set("colorlegend", false);
    model.result("pg1").feature("surf1").set("colortabletrans", "reverse");
    model.result("pg1").feature("surf1").create("sel1", "Selection");
    model.result("pg1").feature("surf1").feature("sel1").selection().named("geom1_csel1_bnd");
    model.result("pg1").run();
    model.result().create("pg2", "PlotGroup2D");
    model.result("pg2").run();
    model.result("pg2").label("\u70b9\u5217\u56fe");
    model.result("pg2").set("showlegendsunit", true);
    model.result("pg2").create("spot1", "SpotDiagram");
    model.result("pg2").feature("spot1").set("transverse", "userdefined");
    model.result("pg2").feature("spot1").set("transverseexpr", new int[]{0, 1, 0});
    model.result("pg2").feature("spot1").set("layout", "rectangular");
    model.result("pg2").feature("spot1").set("columns", 1);
    model.result("pg2").feature("spot1").set("paddingvert", 0);
    model.result("pg2").feature("spot1").set("spotcoordsactive", true);
    model.result("pg2").feature("spot1").set("spotcoordssystem", "global");
    model.result("pg2").feature("spot1").set("spotcoordsprecision", 7);
    model.result("pg2").feature("spot1").set("circleactive", true);
    model.result("pg2").feature("spot1").set("circleradiusexpr", "r_Airy");
    model.result("pg2").feature("spot1").set("fitannotationstospot", true);
    model.result("pg2").feature("spot1").create("col1", "Color");
    model.result("pg2").run();
    model.result("pg2").feature("spot1").feature("col1").set("expr", "at(0,gop.rrel)");
    model.result("pg2").run();

    model.title("\u51ef\u514b\u671b\u8fdc\u955c");

    model
         .description("\u51ef\u514b\u671b\u8fdc\u955c\u662f\u4e00\u4e2a\u76f4\u5f84\u4e3a 10\u00a0\u7c73\u7684\u671b\u8fdc\u955c\uff0c\u91c7\u7528\u4e86\u91cc\u5947-\u514b\u83b1\u7434\u5149\u5b66\u8bbe\u8ba1\u3002\u503c\u5f97\u6ce8\u610f\u7684\u662f\uff0c\u8fd9\u662f\u4e16\u754c\u4e0a\u6700\u65e9\u4f7f\u7528\u8f7b\u8d28\u5206\u5757\u4e3b\u955c\u7684\u5927\u578b\u5149\u5b66\u671b\u8fdc\u955c\u4e4b\u4e00\u3002\u672c\u6559\u7a0b\u6f14\u793a\u5982\u4f55\u4f7f\u7528 COMSOL\u201c\u96f6\u4ef6\u5e93\u201d\u4e2d\u7684\u5185\u7f6e\u591a\u9762\u955c\u6765\u6784\u5efa\u51ef\u514b\u671b\u8fdc\u955c\u7684\u5206\u5757\u4e3b\u955c\u6a21\u578b\u3002");

    return model;
  }

  public static Model run2(Model model) {

    model.mesh().clearMeshes();

    model.sol("sol1").clearSolutionData();

    model.label("keck_telescope.mph");

    return model;
  }

  public static void main(String[] args) {
    Model model = run();
    run2(model);
  }

}
