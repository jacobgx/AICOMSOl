/*
 * newtonian_telescope.java
 */

import com.comsol.model.*;
import com.comsol.model.util.*;

/** Model exported on Apr 30 2026, 12:18 by COMSOL 6.3.0.290. */
public class newtonian_telescope {

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

    model.param().label("\u53c2\u6570 1\uff1a\u671b\u8fdc\u955c\u51e0\u4f55\u5f62\u72b6");
    model.param().create("par2");
    model.param("par2").label("\u53c2\u6570 2\uff1a\u6ce2\u957f\u548c\u573a");

//    To import content from file, use:
//    model.param("par2").loadFile("FILENAME");
    model.param("par2").set("lam", "550[nm]", "\u4e2d\u5fc3\u6ce2\u957f");
    model.param("par2").set("N_hex", "15", "\u516d\u8fb9\u5f62\u5f84\u5411\u73af\u6570");
    model.param("par2").set("theta_x1", "0.0[arcmin]", "\u89c6\u573a\u89d2 (x)");
    model.param("par2").set("theta_y1", "0.0[arcmin]", "\u89c6\u573a\u89d2 (y)");
    model.param("par2").set("theta_x2", "7.5[arcmin]", "\u89c6\u573a\u89d2 (x)");
    model.param("par2").set("theta_y2", "0.0[arcmin]", "\u89c6\u573a\u89d2 (y)");
    model.param("par2").set("theta_x3", "15.0[arcmin]", "\u89c6\u573a\u89d2 (x)");
    model.param("par2").set("theta_y3", "0.0[arcmin]", "\u89c6\u573a\u89d2 (y)");
    model.param("par2").set("vx1", "sin(theta_x1)", "\u5149\u7ebf\u65b9\u5411\u77e2\u91cf\uff0cx \u5206\u91cf");
    model.param("par2").set("vy1", "sin(theta_y1)", "\u5149\u7ebf\u65b9\u5411\u77e2\u91cf\uff0cy \u5206\u91cf");
    model.param("par2")
         .set("vz1", "-sqrt(1-vx1^2-vy1^2)", "\u5149\u7ebf\u65b9\u5411\u77e2\u91cf\uff0cz \u5206\u91cf");
    model.param("par2").set("dx1", "-f*vx1", "\u7269\u5e73\u9762 x \u5750\u6807");
    model.param("par2").set("dy1", "-f*vy1", "\u7269\u5e73\u9762 y \u5750\u6807");
    model.param("par2")
         .set("dz1", "-f*vz1", "\u7269\u5e73\u9762 z \u5750\u6807\uff08\u76f8\u5bf9\u4e8e\u4e3b\u955c\u9876\u70b9\uff09");
    model.param("par2").set("vx2", "sin(theta_x2)", "\u5149\u7ebf\u65b9\u5411\u77e2\u91cf\uff0cx \u5206\u91cf");
    model.param("par2").set("vy2", "sin(theta_y2)", "\u5149\u7ebf\u65b9\u5411\u77e2\u91cf\uff0cy \u5206\u91cf");
    model.param("par2")
         .set("vz2", "-sqrt(1-vx2^2-vy2^2)", "\u5149\u7ebf\u65b9\u5411\u77e2\u91cf\uff0cz \u5206\u91cf");
    model.param("par2").set("dx2", "-f*vx2", "\u7269\u5e73\u9762 x \u5750\u6807");
    model.param("par2").set("dy2", "-f*vy2", "\u7269\u5e73\u9762 y \u5750\u6807");
    model.param("par2")
         .set("dz2", "-f*vz2", "\u7269\u5e73\u9762 z \u5750\u6807\uff08\u76f8\u5bf9\u4e8e\u4e3b\u955c\u9876\u70b9\uff09");
    model.param("par2").set("vx3", "sin(theta_x3)", "\u5149\u7ebf\u65b9\u5411\u77e2\u91cf\uff0cx \u5206\u91cf");
    model.param("par2").set("vy3", "sin(theta_y3)", "\u5149\u7ebf\u65b9\u5411\u77e2\u91cf\uff0cy \u5206\u91cf");
    model.param("par2")
         .set("vz3", "-sqrt(1-vx3^2-vy3^2)", "\u5149\u7ebf\u65b9\u5411\u77e2\u91cf\uff0cz \u5206\u91cf");
    model.param("par2").set("dx3", "-f*vx3", "\u7269\u5e73\u9762 x \u5750\u6807");
    model.param("par2").set("dy3", "-f*vy3", "\u7269\u5e73\u9762 y \u5750\u6807");
    model.param("par2")
         .set("dz3", "-f*vz3", "\u7269\u5e73\u9762 z \u5750\u6807\uff08\u76f8\u5bf9\u4e8e\u4e3b\u955c\u9876\u70b9\uff09");
    model.param("par2").set("theta_Airy", "1.22*lam/d_pupil", "\u827e\u91cc\u6591\u89d2\u534a\u5f84");
    model.param("par2").set("r_Airy", "f*theta_Airy", "\u827e\u91cc\u6591\u534a\u5f84");

    model.component("comp1").geom("geom1").label("\u725b\u987f\u671b\u8fdc\u955c");
    model.component("comp1").geom("geom1").lengthUnit("mm");
    model.component("comp1").geom("geom1").insertFile("newtonian_telescope_geom_sequence.mph", "geom1");
    model.component("comp1").geom("geom1").runPre("fin");
    model.component("comp1").geom("geom1").run();

    model.component("comp1").physics("gop").prop("MaximumSecondary").setIndex("MaximumSecondary", 0, 0);
    model.component("comp1").physics("gop").prop("CountReflections").setIndex("CountReflections", 1, 0);
    model.component("comp1").physics("gop").feature("mp1").set("n_mat", "userdef");
    model.component("comp1").physics("gop").feature("op1").set("lambda0", "lam");
    model.component("comp1").physics("gop").create("relg1", "ReleaseGrid", -1);
    model.component("comp1").physics("gop").feature("relg1").set("GridType", "Hexapolar");
    model.component("comp1").physics("gop").feature("relg1").set("qcc", new String[]{"dx1", "dy1", "dz1"});
    model.component("comp1").physics("gop").feature("relg1").set("rcc", new String[]{"nix", "niy", "niz"});
    model.component("comp1").physics("gop").feature("relg1").set("Rc", "d_pupil/2");
    model.component("comp1").physics("gop").feature("relg1").setIndex("Ncr", "N_hex", 0);
    model.component("comp1").physics("gop").feature("relg1").set("L0", new String[]{"vx1", "vy1", "vz1"});
    model.component("comp1").physics("gop").feature().duplicate("relg2", "relg1");
    model.component("comp1").physics("gop").feature("relg2").set("qcc", new String[]{"dx2", "dy2", "dz2"});
    model.component("comp1").physics("gop").feature("relg2").set("L0", new String[]{"vx2", "vy2", "vz2"});
    model.component("comp1").physics("gop").feature().duplicate("relg3", "relg2");
    model.component("comp1").physics("gop").feature("relg3").set("qcc", new String[]{"dx3", "dy3", "dz3"});
    model.component("comp1").physics("gop").feature("relg3").set("L0", new String[]{"vx3", "vy3", "vz3"});
    model.component("comp1").physics("gop").create("mir1", "Mirror", 2);
    model.component("comp1").physics("gop").feature("mir1").label("\u4e3b\u955c");
    model.component("comp1").physics("gop").feature("mir1").selection().named("geom1_pi1_cylsel1");
    model.component("comp1").physics("gop").create("wall1", "Wall", 2);
    model.component("comp1").physics("gop").feature("wall1").label("\u526f\u955c");
    model.component("comp1").physics("gop").feature("wall1").selection().named("geom1_pi2_cylsel1");
    model.component("comp1").physics("gop").feature("wall1").set("WallCondition", "SpecularReflection");
    model.component("comp1").physics("gop").feature("wall1").set("PrimaryRayCondition", "Expression");
    model.component("comp1").physics("gop").feature("wall1").set("e", "gop.Nrefl>0");
    model.component("comp1").physics("gop").feature("wall1").set("Otherwise", "Pass");
    model.component("comp1").physics("gop").create("wall2", "Wall", 2);
    model.component("comp1").physics("gop").feature("wall2").label("\u6240\u6709\u4e3b\u955c\u969c\u788d\u7269");
    model.component("comp1").physics("gop").feature("wall2").set("WallCondition", "Disappear");
    model.component("comp1").physics("gop").feature("wall2").selection().named("geom1_csel1_bnd");
    model.component("comp1").physics("gop").create("wall3", "Wall", 2);
    model.component("comp1").physics("gop").feature("wall3").label("\u6240\u6709\u526f\u955c\u969c\u788d\u7269");
    model.component("comp1").physics("gop").feature("wall3").set("WallCondition", "Disappear");
    model.component("comp1").physics("gop").feature("wall3").selection().named("geom1_csel2_bnd");
    model.component("comp1").physics("gop").create("wall4", "Wall", 2);
    model.component("comp1").physics("gop").feature("wall4").label("\u50cf\u5e73\u9762");
    model.component("comp1").physics("gop").feature("wall4").selection().named("geom1_csel3_bnd");

    model.component("comp1").mesh("mesh1").autoMeshSize(6);
    model.component("comp1").mesh("mesh1").run();

    model.study("std1").feature("rtrac").set("timestepspec", "specifylength");
    model.study("std1").feature("rtrac").set("lunit", "mm");
    model.study("std1").feature("rtrac").set("llist", "0 2.25*f");
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
    model.result("pg1").feature("rtrj1").feature("col1").set("colortable", "ThermalWave");
    model.result("pg1").run();
    model.result("pg1").feature("rtrj1").feature("filt1").set("type", "logical");
    model.result("pg1").feature("rtrj1").feature("filt1").set("logicalexpr", "gop.Nrefl>0");
    model.result("pg1").run();
    model.result("pg1").feature().duplicate("rtrj2", "rtrj1");
    model.result("pg1").run();
    model.result("pg1").feature("rtrj2").set("inheritplot", "rtrj1");
    model.result("pg1").run();
    model.result("pg1").feature("rtrj2").feature("filt1").set("logicalexpr", "gop.Nrefl==0");
    model.result("pg1").run();
    model.result("pg1").feature("rtrj2").create("tran1", "Transparency");
    model.result("pg1").run();
    model.result("pg1").feature("rtrj2").feature("tran1").set("transparency", 0.8);
    model.result("pg1").run();
    model.result("pg1").create("surf1", "Surface");
    model.result("pg1").feature("surf1").set("coloring", "uniform");
    model.result("pg1").feature("surf1").set("color", "gray");
    model.result("pg1").run();
    model.result().create("pg2", "PlotGroup2D");
    model.result("pg2").run();
    model.result("pg2").label("\u70b9\u5217\u56fe");
    model.result("pg2").set("showlegendsunit", true);
    model.result("pg2").create("spot1", "SpotDiagram");
    model.result("pg2").feature("spot1").set("circleactive", true);
    model.result("pg2").feature("spot1").set("circleradiusexpr", "r_Airy");
    model.result("pg2").feature("spot1").create("col1", "Color");
    model.result("pg2").run();
    model.result("pg2").feature("spot1").feature("col1").set("expr", "at(0,gop.rrel)");
    model.result("pg2").feature("spot1").feature("col1").set("colortable", "Prism");
    model.result("pg2").run();

    model.title("\u725b\u987f\u671b\u8fdc\u955c");

    model
         .description("\u672c\u6559\u7a0b\u6f14\u793a\u5982\u4f55\u901a\u8fc7\u725b\u987f\u671b\u8fdc\u955c\u8ddf\u8e2a\u975e\u504f\u632f\u5149\u7ebf\u3002\u5165\u5c04\u5149\u4ece\u629b\u7269\u9762\u955c\u53cd\u5c04\u5230\u692d\u5706\u5e73\u9762\u526f\u955c\u4e0a\uff0c\u5149\u7a0b\u7531\u6b64\u53d1\u751f 90\u00a0\u5ea6\u504f\u6298\uff0c\u4ece\u800c\u5c06\u5149\u7ebf\u53cd\u5c04\u5230\u7126\u5e73\u9762\u4e0a\u3002\u5728\u671b\u8fdc\u955c\u51e0\u4f55\u7ed3\u6784\u4e2d\uff0c\u4e3b\u955c\u7126\u8ddd\u548c\u76f4\u5f84\u4ee5\u53ca\u7126\u5e73\u9762\u4e0e\u5149\u8f74\u7684\u8ddd\u79bb\u5747\u5df2\u53c2\u6570\u5316\u3002");

    model.mesh().clearMeshes();

    model.sol("sol1").clearSolutionData();

    model.label("newtonian_telescope.mph");

    return model;
  }

  public static void main(String[] args) {
    run();
  }

}
