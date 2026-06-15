/*
 * cylinder_participating_media_p1.java
 */

import com.comsol.model.*;
import com.comsol.model.util.*;

/** Model exported on Apr 30 2026, 10:36 by COMSOL 6.3.0.290. */
public class cylinder_participating_media_p1 {

  public static Model run() {
    Model model = ModelUtil.create("Model");

    model
         .modelPath("D:\\Program Files\\COMSOL\\COMSOL63\\Multiphysics\\applications\\Heat_Transfer_Module\\Verification_Examples");

    model.component().create("comp1", true);

    model.component("comp1").geom().create("geom1", 3);
    model.component("comp1").geom("geom1").geomRep("comsol");

    model.component("comp1").mesh().create("mesh1");
    model.component("comp1").mesh("mesh1").contribute("geom/detail", true);

    model.component("comp1").physics().create("rpm", "ParticipatingMediaRadiation", "geom1");

    model.study().create("std1");
    model.study("std1").create("stat", "Stationary");
    model.study("std1").feature("stat").setSolveFor("/physics/rpm", true);

    model.param().set("T0", "(1[W/m^2]*pi/sigma_const)^(1/4)");
    model.param().descr("T0", "\u4f53\u6e29\u5ea6");
    model.param().set("Tw", "0[K]");
    model.param().descr("Tw", "\u58c1\u6e29");
    model.param().set("ew", "0.5");
    model.param().descr("ew", "\u58c1\u8f90\u5c04\u7387");
    model.param().set("omega", "0.5");
    model.param().descr("omega", "\u5355\u6563\u5c04\u53cd\u5c04\u7387");
    model.param().set("sigma_s", "omega");
    model.param().descr("sigma_s", "\u6563\u5c04\u7cfb\u6570");
    model.param().set("kappa", "sigma_s*(1/omega-1)");
    model.param().descr("kappa", "\u5438\u6536\u7cfb\u6570");
    model.param().set("R", "0.5[m]");
    model.param().descr("R", "\u67f1\u534a\u5f84");
    model.param().set("L", "1[m]");
    model.param().descr("L", "\u67f1\u957f\u5ea6");

    model.component("comp1").geom("geom1").create("cyl1", "Cylinder");
    model.component("comp1").geom("geom1").feature("cyl1").set("r", "R");
    model.component("comp1").geom("geom1").feature("cyl1").set("h", "L");
    model.component("comp1").geom("geom1").runPre("fin");

    model.component("comp1").view("view1").set("transparency", false);

    model.component("comp1").geom("geom1").run();

    model.component("comp1").material().create("mat1", "Common");
    model.component("comp1").material("mat1").label("\u57df");
    model.component("comp1").material("mat1").propertyGroup("def").set("absorption", new String[]{"kappa"});
    model.component("comp1").material("mat1").propertyGroup("def").set("scattering", new String[]{"sigma_s"});
    model.component("comp1").material().create("mat2", "Common");
    model.component("comp1").material("mat2").label("\u58c1");
    model.component("comp1").material("mat2").selection().geom("geom1", 2);
    model.component("comp1").material("mat2").selection().all();
    model.component("comp1").material("mat2").propertyGroup("def").set("emissivity", new String[]{"ew"});

    model.component("comp1").physics("rpm").prop("ParticipatingMediaSettingsProperty")
         .set("RadiationDiscretizationMethod", "P1Approximation");
    model.component("comp1").physics("rpm").feature("rpm1").set("minput_temperature", "T0");
    model.component("comp1").physics("rpm").feature("os1").set("minput_temperature", "Tw");

    model.study("std1").create("param", "Parametric");
    model.study("std1").feature("param").setIndex("pname", "T0", 0);
    model.study("std1").feature("param").setIndex("plistarr", "", 0);
    model.study("std1").feature("param").setIndex("punit", "K", 0);
    model.study("std1").feature("param").setIndex("pname", "T0", 0);
    model.study("std1").feature("param").setIndex("plistarr", "", 0);
    model.study("std1").feature("param").setIndex("punit", "K", 0);
    model.study("std1").feature("param").setIndex("pname", "omega", 0);
    model.study("std1").feature("param").setIndex("plistarr", "0.1 0.5 0.9", 0);
    model.study("std1").createAutoSequences("all");

    model.sol("sol1").runAll();

    model.result().create("pg1", "PlotGroup3D");
    model.result("pg1").label("\u5165\u5c04\u8f90\u5c04 (rpm)");
    model.result("pg1").feature().create("mslc1", "Multislice");
    model.result("pg1").feature("mslc1").set("showsolutionparams", "on");
    model.result("pg1").feature("mslc1").set("smooth", "internal");
    model.result("pg1").feature("mslc1").set("showsolutionparams", "on");
    model.result("pg1").feature("mslc1").set("data", "parent");
    model.result("pg1").run();
    model.result().create("pg2", "PlotGroup1D");
    model.result("pg2").run();
    model.result("pg2").label("\u51c0\u8f90\u5c04\u70ed\u901a\u91cf vs. z\uff0c\u4e00\u7ef4");
    model.result("pg2").create("lngr1", "LineGraph");
    model.result("pg2").feature("lngr1").set("markerpos", "datapoints");
    model.result("pg2").feature("lngr1").set("linewidth", "preference");

    model.component("comp1").view("view1").set("transparency", true);

    model.result("pg2").feature("lngr1").selection().set(12);

    model.component("comp1").view("view1").set("transparency", false);

    model.result("pg2").feature("lngr1").set("expr", "rpm.qr_net");
    model.result("pg2").feature("lngr1").set("descr", "\u51c0\u8f90\u5c04\u70ed\u901a\u91cf");
    model.result("pg2").feature("lngr1").set("xdataexpr", "z");
    model.result("pg2").feature("lngr1").set("xdatadescr", "z \u5750\u6807");
    model.result("pg2").feature("lngr1").set("legend", true);
    model.result("pg2").run();
    model.result("pg2").set("titletype", "none");
    model.result("pg2").set("xlabelactive", true);
    model.result("pg2").set("ylabelactive", true);
    model.result("pg2").run();
    model.result().dataset().create("cln1", "CutLine3D");
    model.result().dataset("cln1").setIndex("genpoints", 0, 1, 0);
    model.result().dataset("cln1").setIndex("genpoints", "L", 1, 2);
    model.result().create("pg3", "PlotGroup1D");
    model.result("pg3").run();
    model.result("pg3").label("\u5165\u5c04\u8f90\u5c04 vs. z\uff0c\u4e00\u7ef4");
    model.result("pg3").set("data", "cln1");
    model.result("pg3").create("lngr1", "LineGraph");
    model.result("pg3").feature("lngr1").set("markerpos", "datapoints");
    model.result("pg3").feature("lngr1").set("linewidth", "preference");
    model.result("pg3").feature("lngr1").set("xdataexpr", "z");
    model.result("pg3").feature("lngr1").set("xdatadescr", "z \u5750\u6807");
    model.result("pg3").feature("lngr1").set("legend", true);
    model.result("pg3").run();
    model.result("pg3").set("titletype", "none");
    model.result("pg3").set("xlabelactive", true);
    model.result("pg3").set("ylabelactive", true);
    model.result("pg3").run();
    model.result().dataset().create("cln2", "CutLine3D");
    model.result().dataset("cln2").setIndex("genpoints", "L/2", 0, 2);
    model.result().dataset("cln2").setIndex("genpoints", "R", 1, 0);
    model.result().dataset("cln2").setIndex("genpoints", "L/2", 1, 2);
    model.result("pg3").run();
    model.result().duplicate("pg4", "pg3");
    model.result("pg4").run();
    model.result("pg4").label("\u5165\u5c04\u8f90\u5c04 vs. x\uff0c\u4e00\u7ef4");
    model.result("pg4").set("data", "cln2");
    model.result("pg4").set("xlabel", "x \u5750\u6807 (m)");
    model.result("pg4").run();
    model.result("pg4").feature("lngr1").set("xdataexpr", "x");
    model.result("pg4").feature("lngr1").set("xdatadescr", "x \u5750\u6807");
    model.result("pg4").run();

    model.component("comp1").physics("rpm").create("os2", "OpaqueSurface", 2);
    model.component("comp1").physics("rpm").feature("os2").set("minput_temperature", "Tw");
    model.component("comp1").physics("rpm").feature("os2").selection().set(1, 2, 5, 6);
    model.component("comp1").physics("rpm").feature("os2").set("epsilon_rad_mat", "userdef");
    model.component("comp1").physics("rpm").feature("os2").set("epsilon_rad", "ew*(1-y/R)");

    model.study("std1").feature("stat").set("useadvanceddisable", true);
    model.study("std1").feature("stat").set("disabledphysics", new String[]{"rpm/os2"});
    model.study().create("std2");
    model.study("std2").create("stat", "Stationary");
    model.study("std2").feature("stat").setSolveFor("/physics/rpm", true);
    model.study("std2").create("param", "Parametric");
    model.study("std2").feature("param").setIndex("pname", "T0", 0);
    model.study("std2").feature("param").setIndex("plistarr", "", 0);
    model.study("std2").feature("param").setIndex("punit", "K", 0);
    model.study("std2").feature("param").setIndex("pname", "T0", 0);
    model.study("std2").feature("param").setIndex("plistarr", "", 0);
    model.study("std2").feature("param").setIndex("punit", "K", 0);
    model.study("std2").feature("param").setIndex("pname", "omega", 0);
    model.study("std2").feature("param").setIndex("plistarr", "0.1 0.5 0.9", 0);
    model.study("std2").createAutoSequences("all");

    model.sol("sol2").runAll();

    model.result().create("pg5", "PlotGroup3D");
    model.result("pg5").label("\u5165\u5c04\u8f90\u5c04 (rpm) 1");
    model.result("pg5").set("data", "dset2");
    model.result("pg5").setIndex("looplevel", 3, 0);
    model.result("pg5").feature().create("mslc1", "Multislice");
    model.result("pg5").feature("mslc1").set("showsolutionparams", "on");
    model.result("pg5").feature("mslc1").set("smooth", "internal");
    model.result("pg5").feature("mslc1").set("showsolutionparams", "on");
    model.result("pg5").feature("mslc1").set("data", "parent");
    model.result("pg5").run();
    model.result().dataset().create("pc1", "ParCurve3D");
    model.result().dataset("pc1").set("data", "dset2");
    model.result().dataset("pc1").set("par1", "phi");
    model.result().dataset("pc1").set("parmax1", "2*pi");
    model.result().dataset("pc1").set("exprx", "R*cos(phi)/2");
    model.result().dataset("pc1").set("expry", "R*sin(phi)/2");
    model.result().dataset("pc1").set("exprz", "L/2");
    model.result().create("pg6", "PlotGroup1D");
    model.result("pg6").run();
    model.result("pg6").label("\u5165\u5c04\u8f90\u5c04 vs. \u65b9\u4f4d\u89d2\uff0c\u4e00\u7ef4");
    model.result("pg6").set("data", "pc1");
    model.result("pg6").create("lngr1", "LineGraph");
    model.result("pg6").feature("lngr1").set("markerpos", "datapoints");
    model.result("pg6").feature("lngr1").set("linewidth", "preference");
    model.result("pg6").feature("lngr1").set("legend", true);
    model.result("pg6").run();
    model.result("pg6").set("titletype", "none");
    model.result("pg6").set("xlabelactive", true);
    model.result("pg6").set("xlabel", "\u65b9\u4f4d\u89d2 (rad)");
    model.result("pg6").set("ylabelactive", true);
    model.result("pg6").run();
    model.result("pg6").feature("lngr1").set("xdata", "expr");
    model.result("pg6").feature("lngr1").set("xdataexpr", "phi");
    model.result("pg6").run();

    model.param().set("a1", "0.99");
    model.param().descr("a1", "Legendre \u7cfb\u6570");

    model.component("comp1").physics("rpm").create("rpm2", "ParticipatingMedium", 3);
    model.component("comp1").physics("rpm").feature("rpm2").selection().set(1);
    model.component("comp1").physics("rpm").feature("rpm2").set("minput_temperature", "T0");
    model.component("comp1").physics("rpm").feature("rpm2").set("ScatteringType", "LinearAnisotropic");
    model.component("comp1").physics("rpm").feature("rpm2").set("a1", "a1");

    model.study("std1").feature("stat").set("disabledphysics", new String[]{"rpm/os2", "rpm/rpm2"});
    model.study("std2").feature("stat").set("useadvanceddisable", true);
    model.study("std2").feature("stat").set("disabledphysics", new String[]{"rpm/rpm2"});
    model.study().create("std3");
    model.study("std3").create("stat", "Stationary");
    model.study("std3").feature("stat").setSolveFor("/physics/rpm", true);
    model.study("std3").create("param", "Parametric");
    model.study("std3").feature("param").setIndex("pname", "T0", 0);
    model.study("std3").feature("param").setIndex("plistarr", "", 0);
    model.study("std3").feature("param").setIndex("punit", "K", 0);
    model.study("std3").feature("param").setIndex("pname", "T0", 0);
    model.study("std3").feature("param").setIndex("plistarr", "", 0);
    model.study("std3").feature("param").setIndex("punit", "K", 0);
    model.study("std3").feature("param").setIndex("pname", "a1", 0);
    model.study("std3").feature("param").setIndex("plistarr", "-0.99 0 0.99", 0);
    model.study("std3").createAutoSequences("all");

    model.sol("sol3").runAll();

    model.result().create("pg7", "PlotGroup3D");
    model.result("pg7").label("\u5165\u5c04\u8f90\u5c04 (rpm) 2");
    model.result("pg7").set("data", "dset3");
    model.result("pg7").setIndex("looplevel", 3, 0);
    model.result("pg7").feature().create("mslc1", "Multislice");
    model.result("pg7").feature("mslc1").set("showsolutionparams", "on");
    model.result("pg7").feature("mslc1").set("smooth", "internal");
    model.result("pg7").feature("mslc1").set("showsolutionparams", "on");
    model.result("pg7").feature("mslc1").set("data", "parent");
    model.result("pg7").run();
    model.result().dataset().create("pc2", "ParCurve3D");
    model.result().dataset("pc2").set("data", "dset3");
    model.result().dataset("pc2").set("expry", "-s*R");
    model.result().dataset("pc2").set("exprz", "L/2");
    model.result().create("pg8", "PlotGroup1D");
    model.result("pg8").run();
    model.result("pg8")
         .label("\u5165\u5c04\u8f90\u5c04 vs. \u5f52\u4e00\u5316\u5149\u5b66\u539a\u5ea6\uff0c\u4e00\u7ef4");
    model.result("pg8").set("data", "pc2");
    model.result("pg8").create("lngr1", "LineGraph");
    model.result("pg8").feature("lngr1").set("markerpos", "datapoints");
    model.result("pg8").feature("lngr1").set("linewidth", "preference");
    model.result("pg8").feature("lngr1").set("xdata", "expr");
    model.result("pg8").feature("lngr1").set("xdataexpr", "s");
    model.result("pg8").feature("lngr1").set("legend", true);
    model.result("pg8").run();
    model.result("pg8").set("titletype", "none");
    model.result("pg8").set("xlabelactive", true);
    model.result("pg8").set("xlabel", "\u5f52\u4e00\u5316\u5149\u5b66\u539a\u5ea6 (y/R)");
    model.result("pg8").set("ylabelactive", true);
    model.result("pg8").run();

    model.title("\u6709\u9650\u5706\u67f1\u5f62\u4ecb\u8d28\u4e2d\u7684\u8f90\u5c04\u4f20\u70ed - P1 \u65b9\u6cd5");

    model
         .description("\u672c\u4f8b\u4f7f\u7528 P1 \u65b9\u6cd5\u6c42\u89e3\u4e00\u4e2a\u53d1\u5c04\u3001\u5438\u6536\u548c\u7ebf\u6027-\u5404\u5411\u5f02\u6027\u6563\u5c04\u7684\u6709\u9650\u5706\u67f1\u5f62\u4ecb\u8d28\u4e2d\u7684\u4e09\u7ef4\u8f90\u5c04\u4f20\u70ed\u95ee\u9898\u3002\u8ba1\u7b97\u5f97\u5230\u7684\u5165\u5c04\u8f90\u5c04\u548c\u70ed\u901a\u91cf\u4e0e\u5728\u76f8\u5e94\u6a21\u578b\u4e2d\u4f7f\u7528\u9ad8\u5ea6\u7cbe\u786e\u7684 S6 \u79bb\u6563\u5750\u6807\u6cd5\u5f97\u5230\u7684\u7ed3\u679c\u8fdb\u884c\u6bd4\u8f83\u3002\u5f53\u5149\u5b66\u539a\u5ea6\u8f83\u5927\u65f6\uff0c\u4e24\u4e2a\u7ed3\u679c\u975e\u5e38\u4e00\u81f4\uff0c\u4f46\u4f7f\u7528 P1 \u65b9\u6cd5\u53ef\u4ee5\u663e\u8457\u964d\u4f4e\u8ba1\u7b97\u6210\u672c\u3002");

    model.mesh().clearMeshes();

    model.sol("sol1").clearSolutionData();
    model.sol("sol2").clearSolutionData();
    model.sol("sol3").clearSolutionData();

    model.label("cylinder_participating_media_p1.mph");

    return model;
  }

  public static void main(String[] args) {
    run();
  }

}
