/*
 * hubble_space_telescope.java
 */

import com.comsol.model.*;
import com.comsol.model.util.*;

/** Model exported on Apr 30 2026, 12:17 by COMSOL 6.3.0.290. */
public class hubble_space_telescope {

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
    model.param().set("theta_x1", "0[arcmin]", "\u89c6\u573a\u89d2 1\uff0cx \u5206\u91cf");
    model.param().set("theta_y1", "0[arcmin]", "\u89c6\u573a\u89d2 1\uff0cy \u5206\u91cf");
    model.param().set("theta_x2", "5[arcmin]", "\u89c6\u573a\u89d2 2\uff0cx \u5206\u91cf");
    model.param().set("theta_y2", "0[arcmin]", "\u89c6\u573a\u89d2 2\uff0cy \u5206\u91cf");
    model.param().set("theta_x3", "10[arcmin]", "\u89c6\u573a\u89d2 3\uff0cx \u5206\u91cf");
    model.param().set("theta_y3", "0[arcmin]", "\u89c6\u573a\u89d2 3\uff0cy \u5206\u91cf");
    model.param().set("N_ring", "10", "\u516d\u8fb9\u73af\u6570");
    model.param().set("P_nom", "2400.0[mm]", "\u6807\u79f0\u5165\u5c04\u5149\u77b3\u76f4\u5f84");
    model.param().set("F_nom", "24.0", "\u6807\u79f0\u7126\u6bd4");
    model.param().set("f_nom", "F_nom*P_nom", "\u6807\u79f0\u7126\u8ddd");
    model.param().set("R_prim", "-11040.0[mm]", "\u4e3b\u955c\u66f2\u7387\u534a\u5f84");
    model.param().set("k_prim", "-1.0022985", "\u4e3b\u955c\u5706\u9525\u5e38\u6570");
    model.param().set("f_prim", "R_prim/2", "\u4e3b\u955c\u7126\u8ddd");
    model.param().set("d0_prim", "2450.0[mm]", "\u4e3b\u955c\u5916\u5f84");
    model.param().set("d1_prim", "0", "\u4e3b\u955c\u9762\u76f4\u5f84");
    model.param().set("dc_prim", "0", "\u4e3b\u955c\u51c0\u76f4\u5f84");
    model.param().set("dh_prim", "600.0[mm]", "\u4e3b\u955c\u4e2d\u5fc3\u5b54\u5f84");
    model.param().set("Tc_prim", "125.0[mm]", "\u4e3b\u955c\u4e2d\u5fc3\u539a\u5ea6");
    model.param().set("Z_prim", "0[mm]", "\u4e3b\u955c z \u4f4d\u7f6e\uff08\u7edd\u5bf9\u503c\uff09");
    model.param().set("np_extra", "10", "\u4e3b\u955c\u989d\u5916\u65b9\u4f4d\u70b9\u6570");
    model.param().set("R_sec", "1358.000[mm]", "\u526f\u955c\u66f2\u7387\u534a\u5f84");
    model.param().set("k_sec", "-1.49600", "\u526f\u955c\u5706\u9525\u5e38\u6570");
    model.param().set("d_sec", "395.0[mm]", "\u526f\u955c\u76f4\u5f84");
    model.param().set("Tc_sec", "75.0[mm]", "\u526f\u955c\u4e2d\u5fc3\u539a\u5ea6");
    model.param()
         .set("Z_sec", "-4906.071[mm]", "\u526f\u955c z \u4f4d\u7f6e\uff08\u76f8\u5bf9\u4e8e\u4e3b\u955c\u9876\u70b9\uff09");
    model.param()
         .set("Z_bfl", "1500.000[mm]", "\u50cf\u9762\u540e\u7126\u8ddd\uff08\u76f8\u5bf9\u4e8e\u4e3b\u955c\u9876\u70b9\uff09");
    model.param()
         .set("Z_image", "Z_sec-Z_bfl", "\u50cf\u9762 z \u4f4d\u7f6e\uff08\u76f8\u5bf9\u4e8e\u526f\u955c\u9876\u70b9\uff09");
    model.param().set("hw_image", "200.0[mm]", "\u50cf\u9762\u534a\u5bbd");
    model.param()
         .set("Z_exit", "-7003.51[mm]", "\u51fa\u5c04\u5149\u77b3 z \u4f4d\u7f6e\uff08\u76f8\u5bf9\u4e8e\u50cf\u9762\uff09");
    model.param()
         .set("Z_obs", "100.0[mm]", "\u969c\u788d\u7269\u504f\u79fb\u91cf\uff08\u81ea\u526f\u955c\u9876\u70b9\uff09");
    model.param().set("eta_obs", "0.33", "\u969c\u788d\u7269\u5206\u6570");
    model.param().set("d0_obs", "eta_obs*P_nom", "\u969c\u788d\u7269\u76f4\u5f84");
    model.param().set("m", "abs(f_nom/f_prim)", "\u526f\u955c\u6a2a\u5411\u653e\u5927\u7387");
    model.param().set("beta", "abs(Z_bfl/f_prim)", "\u540e\u7126\u8ddd\u4e0e\u4e3b\u955c\u7126\u8ddd\u4e4b\u6bd4");
    model.param().set("Cp", "2*(1/R_sec-1/R_prim)", "Petzval \u66f2\u7387");
    model.param().set("max_prim", "50.0[mm]", "\u6700\u5927\u5355\u5143\u5927\u5c0f\uff0c\u4e3b\u955c");
    model.param().set("max_sec", "max_prim*d_sec/dc_prim", "\u6700\u5927\u5355\u5143\u5927\u5c0f\uff0c\u526f\u955c");
    model.param().set("vz", "1", "\u5149\u7ebf\u65b9\u5411\u77e2\u91cf\uff0cz \u5206\u91cf");
    model.param().set("vx1", "tan(theta_x1)", "\u5149\u7ebf\u65b9\u5411\u77e2\u91cf\uff0cx \u5206\u91cf");
    model.param().set("vy1", "tan(theta_y1)", "\u5149\u7ebf\u65b9\u5411\u77e2\u91cf\uff0cy \u5206\u91cf");
    model.param().set("vx2", "tan(theta_x2)", "\u5149\u7ebf\u65b9\u5411\u77e2\u91cf\uff0cx \u5206\u91cf");
    model.param().set("vy2", "tan(theta_y2)", "\u5149\u7ebf\u65b9\u5411\u77e2\u91cf\uff0cy \u5206\u91cf");
    model.param().set("vx3", "tan(theta_x3)", "\u5149\u7ebf\u65b9\u5411\u77e2\u91cf\uff0cx \u5206\u91cf");
    model.param().set("vy3", "tan(theta_y3)", "\u5149\u7ebf\u65b9\u5411\u77e2\u91cf\uff0cy \u5206\u91cf");
    model.param().set("dz", "2*Z_obs-Z_sec", "\u5149\u7ebf\u53d1\u5c04 z \u5750\u6807");
    model.param().set("dx1", "dz*tan(theta_x1)", "\u5149\u7ebf\u53d1\u5c04\uff0c\u89c6\u573a 1\uff0cx \u5750\u6807");
    model.param().set("dy1", "dz*tan(theta_y1)", "\u5149\u7ebf\u53d1\u5c04\uff0c\u89c6\u573a 1\uff0cy \u5750\u6807");
    model.param().set("dx2", "dz*tan(theta_x2)", "\u5149\u7ebf\u53d1\u5c04\uff0c\u89c6\u573a 2\uff0cx \u5750\u6807");
    model.param().set("dy2", "dz*tan(theta_y2)", "\u5149\u7ebf\u53d1\u5c04\uff0c\u89c6\u573a 2\uff0cy \u5750\u6807");
    model.param().set("dx3", "dz*tan(theta_x3)", "\u5149\u7ebf\u53d1\u5c04\uff0c\u89c6\u573a 3\uff0cx \u5750\u6807");
    model.param().set("dy3", "dz*tan(theta_y3)", "\u5149\u7ebf\u53d1\u5c04\uff0c\u89c6\u573a 3\uff0cy \u5750\u6807");
    model.param().set("theta_Airy", "1.22*lam_vac/P_nom", "\u827e\u91cc\u6591\u89d2\u534a\u5f84");
    model.param().set("r_Airy", "f_nom*theta_Airy", "\u827e\u91cc\u6591\u534a\u5f84");

    model.geom()
         .load(new String[]{"part1"}, "Ray_Optics_Module\\3D\\Mirrors\\conic_mirror_on_axis_3d.mph", new String[]{"part1"});
    model.component("comp1").geom("geom1").create("pi1", "PartInstance");
    model.component("comp1").geom("geom1").feature("pi1").set("selkeepnoncontr", false);
    model.component("comp1").geom("geom1").feature("pi1").set("part", "part1");
    model.component("comp1").geom("geom1").feature("pi1").label("\u4e3b\u955c");
    model.component("comp1").geom("geom1").feature("pi1").setEntry("inputexpr", "R", "R_prim");
    model.component("comp1").geom("geom1").feature("pi1").setEntry("inputexpr", "k", "k_prim");
    model.component("comp1").geom("geom1").feature("pi1").setEntry("inputexpr", "Tc", "Tc_prim");
    model.component("comp1").geom("geom1").feature("pi1").setEntry("inputexpr", "d0", "d0_prim");
    model.component("comp1").geom("geom1").feature("pi1").setEntry("inputexpr", "d1", 0);
    model.component("comp1").geom("geom1").feature("pi1").setEntry("inputexpr", "d_clear", 0);
    model.component("comp1").geom("geom1").feature("pi1").setEntry("inputexpr", "d_hole", "dh_prim");
    model.component("comp1").geom("geom1").feature("pi1").setEntry("inputexpr", "nix", "nix");
    model.component("comp1").geom("geom1").feature("pi1").setEntry("inputexpr", "niy", "niy");
    model.component("comp1").geom("geom1").feature("pi1").setEntry("inputexpr", "niz", "niz");
    model.component("comp1").geom("geom1").feature("pi1").setEntry("inputexpr", "n_extra_a", "np_extra");
    model.component("comp1").geom("geom1").feature("pi1").set("displ", new String[]{"0", "0", "Z_prim"});
    model.component("comp1").geom("geom1").run("pi1");
    model.component("comp1").geom("geom1").feature("pi1").setEntry("selkeepbnd", "pi1_cylsel1", true);
    model.component("comp1").geom("geom1").selection().create("csel1", "CumulativeSelection");
    model.component("comp1").geom("geom1").selection("csel1").label("\u969c\u788d\u7269");
    model.component("comp1").geom("geom1").feature("pi1").setEntry("selcontributetobnd", "pi1_cylsel2", "csel1");
    model.component("comp1").geom("geom1").feature("pi1").setEntry("selcontributetobnd", "pi1_comsel2", "csel1");
    model.component("comp1").geom("geom1").create("pi2", "PartInstance");
    model.component("comp1").geom("geom1").feature("pi2").set("selkeepnoncontr", false);
    model.component("comp1").geom("geom1").feature("pi2").set("part", "part1");
    model.component("comp1").geom("geom1").feature("pi2").label("\u526f\u955c");
    model.component("comp1").geom("geom1").feature("pi2").setEntry("inputexpr", "R", "R_sec");
    model.component("comp1").geom("geom1").feature("pi2").setEntry("inputexpr", "k", "k_sec");
    model.component("comp1").geom("geom1").feature("pi2").setEntry("inputexpr", "Tc", "Tc_sec");
    model.component("comp1").geom("geom1").feature("pi2").setEntry("inputexpr", "d0", "d_sec");
    model.component("comp1").geom("geom1").feature("pi2").setEntry("inputexpr", "d1", 0);
    model.component("comp1").geom("geom1").feature("pi2").setEntry("inputexpr", "d_clear", 0);
    model.component("comp1").geom("geom1").feature("pi2").setEntry("inputexpr", "d_hole", 0);
    model.component("comp1").geom("geom1").feature("pi2").set("workplanesrc", "pi1");
    model.component("comp1").geom("geom1").feature("pi2").set("workplane", "wp1");
    model.component("comp1").geom("geom1").feature("pi2").set("displ", new String[]{"0", "0", "Z_sec"});
    model.component("comp1").geom("geom1").run("pi2");
    model.component("comp1").geom("geom1").feature("pi2").setEntry("selkeepbnd", "pi2_cylsel1", true);
    model.component("comp1").geom("geom1").feature("pi2").setEntry("selcontributetobnd", "pi2_cylsel2", "csel1");
    model.component("comp1").geom("geom1").feature("pi2").setEntry("selcontributetobnd", "pi2_comsel2", "csel1");
    model.component("comp1").geom("geom1").create("ps1", "ParametricSurface");
    model.component("comp1").geom("geom1").feature("ps1").label("\u50cf\u9762");
    model.component("comp1").geom("geom1").feature("ps1").set("parmin1", "-hw_image");
    model.component("comp1").geom("geom1").feature("ps1").set("parmax1", "hw_image");
    model.component("comp1").geom("geom1").feature("ps1").set("parmin2", "-hw_image");
    model.component("comp1").geom("geom1").feature("ps1").set("parmax2", "hw_image");
    model.component("comp1").geom("geom1").feature("ps1")
         .set("coord", new String[]{"s1", "s2", "Cp*(s1^2 + s2^2)/(1 + sqrt(1 - Cp^2*(s1^2 + s2^2)))*1[m]"});
    model.component("comp1").geom("geom1").feature("ps1").set("pos", new String[]{"0", "0", "Z_image"});
    model.component("comp1").geom("geom1").feature("ps1").set("workplanesrc", "pi2");
    model.component("comp1").geom("geom1").feature("ps1").set("workplane", "wp1");
    model.component("comp1").geom("geom1").feature("ps1").set("selresult", true);
    model.geom()
         .load(new String[]{"part2"}, "Ray_Optics_Module\\3D\\Apertures_and_Obstructions\\circular_planar_annulus.mph", new String[]{"part1"});
    model.component("comp1").geom("geom1").run("ps1");
    model.component("comp1").geom("geom1").create("pi3", "PartInstance");
    model.component("comp1").geom("geom1").feature("pi3").set("selkeepnoncontr", false);
    model.component("comp1").geom("geom1").feature("pi3").set("part", "part2");
    model.component("comp1").geom("geom1").feature("pi3").label("\u6b21\u7ea7\u969c\u788d\u7269");
    model.component("comp1").geom("geom1").feature("pi3").setEntry("inputexpr", "d0", "d0_obs");
    model.component("comp1").geom("geom1").feature("pi3").setEntry("inputexpr", "d1", 0);
    model.component("comp1").geom("geom1").feature("pi3").set("workplanesrc", "pi2");
    model.component("comp1").geom("geom1").feature("pi3").set("workplane", "wp1");
    model.component("comp1").geom("geom1").feature("pi3").set("displ", new String[]{"0", "0", "Z_obs"});
    model.component("comp1").geom("geom1").feature("pi3").setEntry("selcontributetobnd", "pi3_sel1", "csel1");
    model.component("comp1").geom("geom1").run("pi3");
    model.component("comp1").geom("geom1").run();

    model.component("comp1").physics("gop").selection().set();
    model.component("comp1").physics("gop").prop("MaximumSecondary").setIndex("MaximumSecondary", 0, 0);
    model.component("comp1").physics("gop").create("mir1", "Mirror", 2);
    model.component("comp1").physics("gop").feature("mir1").label("\u4e3b\u955c");
    model.component("comp1").physics("gop").feature("mir1").selection().named("geom1_pi1_cylsel1");
    model.component("comp1").physics("gop").create("mir2", "Mirror", 2);
    model.component("comp1").physics("gop").feature("mir2").label("\u526f\u955c");
    model.component("comp1").physics("gop").feature("mir2").selection().named("geom1_pi2_cylsel1");
    model.component("comp1").physics("gop").create("wall1", "Wall", 2);
    model.component("comp1").physics("gop").feature("wall1").label("\u969c\u788d\u7269");
    model.component("comp1").physics("gop").feature("wall1").selection().named("geom1_csel1_bnd");
    model.component("comp1").physics("gop").feature("wall1").set("WallCondition", "Disappear");
    model.component("comp1").physics("gop").create("wall2", "Wall", 2);
    model.component("comp1").physics("gop").feature("wall2").label("\u50cf");
    model.component("comp1").physics("gop").feature("wall2").selection().named("geom1_ps1_bnd");
    model.component("comp1").physics("gop").create("relg1", "ReleaseGrid", -1);
    model.component("comp1").physics("gop").feature("relg1").set("GridType", "Hexapolar");
    model.component("comp1").physics("gop").feature("relg1").set("qcc", new String[]{"-dx1", "-dy1", "dz"});
    model.component("comp1").physics("gop").feature("relg1").set("rcc", new int[]{0, 0, 1});
    model.component("comp1").physics("gop").feature("relg1").set("Rc", "P_nom/2");
    model.component("comp1").physics("gop").feature("relg1").setIndex("Ncr", "N_ring", 0);
    model.component("comp1").physics("gop").feature("relg1").set("L0", new String[]{"vx1", "vy1", "-vz"});
    model.component("comp1").physics("gop").feature().duplicate("relg2", "relg1");
    model.component("comp1").physics("gop").feature("relg2").set("qcc", new String[]{"-dx2", "-dy2", "dz"});
    model.component("comp1").physics("gop").feature("relg2").set("L0", new String[]{"vx2", "vy2", "-vz"});
    model.component("comp1").physics("gop").feature().duplicate("relg3", "relg2");
    model.component("comp1").physics("gop").feature("relg3").set("qcc", new String[]{"-dx3", "-dy3", "dz"});
    model.component("comp1").physics("gop").feature("relg3").set("L0", new String[]{"vx3", "vy3", "-vz"});

    model.component("comp1").mesh("mesh1").run();

    model.study("std1").feature("rtrac").set("timestepspec", "specifylength");
    model.study("std1").feature("rtrac").set("llist", "0 17");
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
    model.result("pg1").set("titletype", "none");
    model.result("pg1").set("showlegendsmaxmin", true);
    model.result("pg1").set("showlegendsunit", true);
    model.result("pg1").run();
    model.result("pg1").feature("rtrj1").feature("col1").set("expr", "at('last',gop.rrel)");
    model.result("pg1").feature("rtrj1").feature("col1").set("unit", "\u00b5m");
    model.result("pg1").run();
    model.result("pg1").create("surf1", "Surface");
    model.result("pg1").feature("surf1").set("coloring", "uniform");
    model.result("pg1").feature("surf1").set("color", "gray");
    model.result("pg1").feature("surf1").create("sel1", "Selection");
    model.result("pg1").feature("surf1").feature("sel1").selection().named("geom1_csel1_bnd");
    model.result("pg1").run();
    model.result("pg1").create("surf2", "Surface");
    model.result("pg1").feature("surf2").set("coloring", "uniform");
    model.result("pg1").feature("surf2").set("color", "custom");
    model.result("pg1").feature("surf2")
         .set("customcolor", new double[]{0.7411764860153198, 0.7882353067398071, 0.8470588326454163});
    model.result("pg1").feature("surf2").create("sel1", "Selection");
    model.result("pg1").feature("surf2").feature("sel1").selection().set(4, 11);
    model.result("pg1").run();
    model.result().create("pg2", "PlotGroup2D");
    model.result("pg2").run();
    model.result("pg2").label("\u70b9\u5217\u56fe");
    model.result("pg2").set("showlegendsunit", true);
    model.result("pg2").create("spot1", "SpotDiagram");
    model.result("pg2").feature("spot1").set("layout", "rectangular");
    model.result("pg2").feature("spot1").set("paddinghoriz", 0);
    model.result("pg2").feature("spot1").set("circleactive", true);
    model.result("pg2").feature("spot1").set("circleradiusexpr", "r_Airy");
    model.result("pg2").feature("spot1").create("col1", "Color");
    model.result("pg2").run();
    model.result("pg2").feature("spot1").feature("col1").set("expr", "at(0,gop.rrel)");
    model.result("pg2").run();

    model.title("\u54c8\u52c3\u592a\u7a7a\u671b\u8fdc\u955c");

    model
         .description("\u54c8\u52c3\u592a\u7a7a\u671b\u8fdc\u955c (HST) \u662f\u4e00\u4e2a\u6807\u51c6\u5361\u585e\u683c\u6797\u671b\u8fdc\u955c\u7684\u4f8b\u5b50\u3002\u672c\u4f8b\u4f7f\u7528 COMSOL \u96f6\u4ef6\u5e93\u4e2d\u7684\u201c\u540c\u8f74\u9525\u9762\u955c\uff08\u4e09\u7ef4\uff09\u201d\u96f6\u4ef6\u6765\u6784\u9020 HST \u91cc\u5947-\u514b\u83b1\u7434\u51e0\u4f55\u7ed3\u6784\uff0c\u5e76\u4f7f\u7528\u591a\u4e2a\u91ca\u653e\u7279\u5f81\u6765\u751f\u6210\u8f74\u4e0a\u548c\u8f74\u5916\u5149\u7ebf\u4ee5\u53ca\u70b9\u5217\u56fe\u3002");

    model.mesh().clearMeshes();

    model.sol("sol1").clearSolutionData();

    model.label("hubble_space_telescope.mph");

    return model;
  }

  public static void main(String[] args) {
    run();
  }

}
