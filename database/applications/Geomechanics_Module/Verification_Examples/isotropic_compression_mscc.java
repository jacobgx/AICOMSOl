/*
 * isotropic_compression_mscc.java
 */

import com.comsol.model.*;
import com.comsol.model.util.*;

/** Model exported on Apr 30 2026, 10:16 by COMSOL 6.3.0.290. */
public class isotropic_compression_mscc {

  public static Model run() {
    Model model = ModelUtil.create("Model");

    model
         .modelPath("D:\\Program Files\\COMSOL\\COMSOL63\\Multiphysics\\applications\\Geomechanics_Module\\Verification_Examples");

    model.component().create("comp1", true);

    model.component("comp1").geom().create("geom1", 2);
    model.component("comp1").geom("geom1").axisymmetric(true);

    model.component("comp1").mesh().create("mesh1");

    model.component("comp1").physics().create("solid", "SolidMechanics", "geom1");

    model.study().create("std1");
    model.study("std1").create("stat", "Stationary");
    model.study("std1").feature("stat").setSolveFor("/physics/solid", true);

    model.param().label("\u9ecf\u571f\u6750\u6599\u5c5e\u6027");

//    To import content from file, use:
//    model.param().loadFile("FILENAME");
    model.param().set("p0", "200[kPa]", "\u521d\u59cb\u538b\u529b");
    model.param().set("GG", "3000[kPa]", "\u526a\u5207\u6a21\u91cf");
    model.param().set("Rho", "2000[kg/m^3]", "\u5bc6\u5ea6");
    model.param().set("MM", "1.15", "\u4e34\u754c\u72b6\u6001\u7ebf\u7684\u659c\u7387");
    model.param().set("lambdas", "0.147", "\u53d8\u6027\u571f\u7684\u538b\u7f29\u6307\u6570");
    model.param().set("kappas", "0.027", "\u7ed3\u6784\u6027\u571f\u7684\u81a8\u80c0\u6307\u6570");
    model.param().set("ee", "1.92", "\u53c2\u8003\u538b\u529b\u7684\u7a7a\u9699\u6bd4");
    model.param().set("deltae", "0.62", "\u9644\u52a0\u7a7a\u9699\u6bd4");
    model.param().set("zeta", "2", "\u5851\u6027\u52bf\u53c2\u6570");
    model.param().set("dvs", "0.6", "\u4f53\u79ef\u53d8\u5f62\u7684\u89e3\u6784\u6307\u6570");
    model.param().set("dss", "1", "\u526a\u5207\u53d8\u5f62\u7684\u89e3\u6784\u6307\u6570");
    model.param().set("Pc0", "100[kPa]", "\u521d\u59cb\u56fa\u7ed3\u538b\u529b");
    model.param().set("Pbi", "30[kPa]", "\u521d\u59cb\u7ed3\u6784\u5f3a\u5ea6");
    model.param("default").paramCase().create("case1");
    model.param("default").paramCase("case1").label("\u5929\u7136\u5927\u962a\u9ecf\u571f");
    model.param("default").paramCase().create("case2");
    model.param("default").paramCase("case2").set("p0", "800[kPa]");
    model.param("default").paramCase("case2").set("GG", "45000[kPa]");
    model.param("default").paramCase("case2").set("MM", "1.30");
    model.param("default").paramCase("case2").set("lambdas", "0.025");
    model.param("default").paramCase("case2").set("kappas", "0.009");
    model.param("default").paramCase("case2").set("ee", "0.67");
    model.param("default").paramCase("case2").set("deltae", "0.085");
    model.param("default").paramCase("case2").set("zeta", "1.5");
    model.param("default").paramCase("case2").set("dvs", "0.7");
    model.param("default").paramCase("case2").set("Pc0", "4150[kPa]");
    model.param("default").paramCase("case2").set("Pbi", "300[kPa]");
    model.param("default").paramCase("case2").label("\u5929\u7136\u6ce5\u7070\u8d28\u9ecf\u571f");
    model.param("default").paramCase().create("case3");
    model.param("default").paramCase("case3").set("GG", "6000[kPa]");
    model.param("default").paramCase("case3").set("MM", "1.60");
    model.param("default").paramCase("case3").set("lambdas", "0.44");
    model.param("default").paramCase("case3").set("kappas", "0.06");
    model.param("default").paramCase("case3").set("ee", "4.37");
    model.param("default").paramCase("case3").set("deltae", "1.50");
    model.param("default").paramCase("case3").set("zeta", "1.8");
    model.param("default").paramCase("case3").set("dvs", "0.15");
    model.param("default").paramCase("case3").set("dss", "10");
    model.param("default").paramCase("case3").set("Pc0", "50[kPa]");
    model.param("default").paramCase("case3").set("Pbi", "50[kPa]");
    model.param("default").paramCase("case3").label("\u542b\u6c34\u6ce5\u7684\u6709\u660e\u9ecf\u571f\uff0cAw = 6%");
    model.param("default").paramCase().create("case4");
    model.param("default").paramCase("case4").set("p0", "300[kPa]");
    model.param("default").paramCase("case4").set("GG", "8000[kPa]");
    model.param("default").paramCase("case4").set("MM", "1.45");
    model.param("default").paramCase("case4").set("lambdas", "0.44");
    model.param("default").paramCase("case4").set("kappas", "0.024");
    model.param("default").paramCase("case4").set("ee", "4.37");
    model.param("default").paramCase("case4").set("deltae", "2.25");
    model.param("default").paramCase("case4").set("zeta", "0.5");
    model.param("default").paramCase("case4").set("dvs", "0.01");
    model.param("default").paramCase("case4").set("dss", "10");
    model.param("default").paramCase("case4").set("Pc0", "200[kPa]");
    model.param("default").paramCase("case4").set("Pbi", "100[kPa]");
    model.param("default").paramCase("case4").label("\u542b\u6c34\u6ce5\u7684\u6709\u660e\u9ecf\u571f\uff0cAw = 9%");
    model.param("default").paramCase().create("case5");
    model.param("default").paramCase("case5").set("p0", "400[kPa]");
    model.param("default").paramCase("case5").set("GG", "40000[kPa]");
    model.param("default").paramCase("case5").set("MM", "1.35");
    model.param("default").paramCase("case5").set("lambdas", "0.44");
    model.param("default").paramCase("case5").set("kappas", "0.001");
    model.param("default").paramCase("case5").set("ee", "4.37");
    model.param("default").paramCase("case5").set("deltae", "2.65");
    model.param("default").paramCase("case5").set("zeta", "0.1");
    model.param("default").paramCase("case5").set("dvs", "0.001");
    model.param("default").paramCase("case5").set("dss", "30");
    model.param("default").paramCase("case5").set("Pc0", "1800[kPa]");
    model.param("default").paramCase("case5").set("Pbi", "650[kPa]");
    model.param("default").paramCase("case5")
         .label("\u542b\u6c34\u6ce5\u7684\u6709\u660e\u9ecf\u571f\uff0cAw = 18%");
    model.param("default").paramCase().create("case6");
    model.param("default").paramCase("case6").set("p0", "400[kPa]");
    model.param("default").paramCase("case6").set("GG", "14000[kPa]");
    model.param("default").paramCase("case6").set("MM", "1.13");
    model.param("default").paramCase("case6").set("lambdas", "0.26");
    model.param("default").paramCase("case6").set("kappas", "0.02");
    model.param("default").paramCase("case6").set("ee", "2.86");
    model.param("default").paramCase("case6").set("deltae", "0.55");
    model.param("default").paramCase("case6").set("zeta", "1.5");
    model.param("default").paramCase("case6").set("dvs", "0.02");
    model.param("default").paramCase("case6").set("dss", "10");
    model.param("default").paramCase("case6").set("Pc0", "150[kPa]");
    model.param("default").paramCase("case6").set("Pbi", "60[kPa]");
    model.param("default").paramCase("case6").label("\u542b\u6c34\u6ce5\u7684\u66fc\u8c37\u9ecf\u571f\uff0cAw = 5%");
    model.param("default").paramCase().create("case7");
    model.param("default").paramCase("case7").set("p0", "450[kPa]");
    model.param("default").paramCase("case7").set("GG", "16000[kPa]");
    model.param("default").paramCase("case7").set("MM", "1.13");
    model.param("default").paramCase("case7").set("lambdas", "0.26");
    model.param("default").paramCase("case7").set("kappas", "0.01");
    model.param("default").paramCase("case7").set("ee", "2.86");
    model.param("default").paramCase("case7").set("deltae", "0.60");
    model.param("default").paramCase("case7").set("zeta", "0.2");
    model.param("default").paramCase("case7").set("dvs", "0.01");
    model.param("default").paramCase("case7").set("dss", "30");
    model.param("default").paramCase("case7").set("Pc0", "430[kPa]");
    model.param("default").paramCase("case7").set("Pbi", "400[kPa]");
    model.param("default").paramCase("case7")
         .label("\u542b\u6c34\u6ce5\u7684\u66fc\u8c37\u9ecf\u571f\uff0cAw = 10%");
    model.param("default").paramCase().create("case8");
    model.param("default").paramCase("case8").set("p0", "500[kPa]");
    model.param("default").paramCase("case8").set("GG", "30000[kPa]");
    model.param("default").paramCase("case8").set("MM", "1.13");
    model.param("default").paramCase("case8").set("lambdas", "0.26");
    model.param("default").paramCase("case8").set("kappas", "0.005");
    model.param("default").paramCase("case8").set("ee", "2.86");
    model.param("default").paramCase("case8").set("deltae", "0.75");
    model.param("default").paramCase("case8").set("zeta", "0.1");
    model.param("default").paramCase("case8").set("dvs", "0.01");
    model.param("default").paramCase("case8").set("dss", "30");
    model.param("default").paramCase("case8").set("Pc0", "600[kPa]");
    model.param("default").paramCase("case8").set("Pbi", "500[kPa]");
    model.param("default").paramCase("case8")
         .label("\u542b\u6c34\u6ce5\u7684\u66fc\u8c37\u9ecf\u571f\uff0cAw = 15%");
    model.param().create("par2");
    model.param("par2").set("para", "0");
    model.param("par2").descr("para", "\u53c2\u6570");

    model.func().create("int1", "Interpolation");
    model.func("int1").label("\u8fb9\u754c\u8f7d\u8377");
    model.func("int1").set("funcname", "Pressure");
    model.func("int1").setIndex("table", 0, 0, 0);
    model.func("int1").setIndex("table", "0.01*p0", 0, 1);
    model.func("int1").setIndex("table", 1, 1, 0);
    model.func("int1").setIndex("table", "10*p0", 1, 1);
    model.func("int1").setIndex("table", 2, 2, 0);
    model.func("int1").setIndex("table", "50*p0", 2, 1);

    model.component("comp1").geom("geom1").create("r1", "Rectangle");
    model.component("comp1").geom("geom1").feature("r1").set("size", new String[]{"5[cm]", "10[cm]"});
    model.component("comp1").geom("geom1").run("r1");
    model.component("comp1").geom("geom1").run();

    model.component("comp1").material().create("mat1", "Common");
    model.component("comp1").material("mat1").label("\u9ecf\u571f\u6750\u6599");

    model.component("comp1").physics("solid").create("epsm1", "ElastoplasticSoilMaterial", 2);
    model.component("comp1").physics("solid").feature("epsm1").selection().all();
    model.component("comp1").physics("solid").feature("epsm1").set("MaterialModel", "StructuredCamClay");
    model.component("comp1").physics("solid").feature("epsm1").set("CamClayOption", "G");
    model.component("comp1").physics("solid").feature("epsm1").set("epdevc_mat", "userdef");
    model.component("comp1").physics("solid").feature("epsm1").set("epdevc", 0.1);
    model.component("comp1").physics("solid").feature("epsm1").set("pref", "1[kPa]");
    model.component("comp1").physics("solid").feature("epsm1").set("pc0", "Pc0");
    model.component("comp1").physics("solid").feature("epsm1").set("localMethod", "backwardEuler");
    model.component("comp1").physics("solid").feature("epsm1").set("maxit", 50);
    model.component("comp1").physics("solid").feature("epsm1").set("reltol", "1e-8");
    model.component("comp1").physics("solid").create("bndl1", "BoundaryLoad", 1);
    model.component("comp1").physics("solid").feature("bndl1").selection().set(3, 4);
    model.component("comp1").physics("solid").feature("bndl1").set("forceType", "FollowerPressure");
    model.component("comp1").physics("solid").feature("bndl1").set("pressure", "Pressure(para)");
    model.component("comp1").physics("solid").create("roll1", "Roller", 1);
    model.component("comp1").physics("solid").feature("roll1").selection().set(2);

    model.component("comp1").material("mat1").propertyGroup().create("KG", "KG", "Bulk_modulus_and_shear_modulus");
    model.component("comp1").material("mat1").propertyGroup("KG").set("G", new String[]{"GG"});
    model.component("comp1").material("mat1").propertyGroup()
         .create("StructuredCamClayModel", "StructuredCamClayModel", "Structured_Camclay");
    model.component("comp1").material("mat1").propertyGroup("StructuredCamClayModel")
         .set("kappaSwellingS", new String[]{"kappas"});
    model.component("comp1").material("mat1").propertyGroup("StructuredCamClayModel")
         .set("lambdaCompS", new String[]{"lambdas"});
    model.component("comp1").material("mat1").propertyGroup("StructuredCamClayModel")
         .set("evoidrefS", new String[]{"ee"});
    model.component("comp1").material("mat1").propertyGroup("StructuredCamClayModel")
         .set("dvS", new String[]{"dvs"});
    model.component("comp1").material("mat1").propertyGroup("StructuredCamClayModel")
         .set("dsS", new String[]{"dss"});
    model.component("comp1").material("mat1").propertyGroup("StructuredCamClayModel").set("M", new String[]{"MM"});
    model.component("comp1").material("mat1").propertyGroup("StructuredCamClayModel")
         .set("Deltaei", new String[]{"deltae"});
    model.component("comp1").material("mat1").propertyGroup("StructuredCamClayModel")
         .set("pbi", new String[]{"Pbi"});
    model.component("comp1").material("mat1").propertyGroup("StructuredCamClayModel")
         .set("zetaS", new String[]{"zeta"});
    model.component("comp1").material("mat1").propertyGroup("def").set("density", new String[]{"Rho"});

    model.component("comp1").mesh("mesh1").create("map1", "Map");
    model.component("comp1").mesh("mesh1").feature("size").set("hauto", 7);
    model.component("comp1").mesh("mesh1").run();

    model.study("std1").setGenPlots(false);
    model.study("std1").create("param", "Parametric");
    model.study("std1").feature("param").set("sweeptype", "switch");
    model.study("std1").feature("param").setIndex("switchname", "default", 0);
    model.study("std1").feature("param").setIndex("switchcase", "all", 0);
    model.study("std1").feature("param").setIndex("switchlistarr", "range(1,1,8)", 0);
    model.study("std1").feature("param").setIndex("switchname", "default", 0);
    model.study("std1").feature("param").setIndex("switchcase", "all", 0);
    model.study("std1").feature("param").setIndex("switchlistarr", "range(1,1,8)", 0);
    model.study("std1").feature("stat").set("useparam", true);
    model.study("std1").feature("stat").setIndex("pname", "deltae", 0);
    model.study("std1").feature("stat").setIndex("plistarr", "", 0);
    model.study("std1").feature("stat").setIndex("punit", "", 0);
    model.study("std1").feature("stat").setIndex("pname", "deltae", 0);
    model.study("std1").feature("stat").setIndex("plistarr", "", 0);
    model.study("std1").feature("stat").setIndex("punit", "", 0);
    model.study("std1").feature("stat").setIndex("pname", "para", 0);
    model.study("std1").feature("stat")
         .setIndex("plistarr", "range(0,0.001,0.01) range(0.015,0.005,1) range(1.02,0.02,2)", 0);
    model.study("std1").createAutoSequences("all");

    model.sol().create("sol2");
    model.sol("sol2").study("std1");
    model.sol("sol2").label("\u53c2\u6570\u5316\u89e3 1");

    model.batch("p1").feature("so1").set("psol", "sol2");
    model.batch("p1").run("compute");

    model.result().create("pg1", "PlotGroup1D");
    model.result("pg1").run();
    model.result("pg1").label("\u5929\u7136\u5927\u962a\u9ecf\u571f");
    model.result("pg1").set("data", "dset2");
    model.result("pg1").setIndex("looplevelinput", "manual", 1);
    model.result("pg1").setIndex("looplevel", new int[]{1}, 1);
    model.result("pg1").set("xlog", true);
    model.result("pg1").set("titletype", "none");
    model.result("pg1").set("xlabelactive", true);
    model.result("pg1").set("xlabel", "\u538b\u529b (kPa)");
    model.result("pg1").set("ylabelactive", true);
    model.result("pg1").set("ylabel", "\u7a7a\u9699\u6bd4 (1)");
    model.result("pg1").create("ptgr1", "PointGraph");
    model.result("pg1").feature("ptgr1").set("markerpos", "datapoints");
    model.result("pg1").feature("ptgr1").set("linewidth", "preference");
    model.result("pg1").feature("ptgr1").selection().set(4);
    model.result("pg1").feature("ptgr1").set("expr", "solid.epsm1.evoid");
    model.result("pg1").feature("ptgr1").set("descr", "\u7a7a\u9699\u6bd4");
    model.result("pg1").feature("ptgr1").set("xdataexpr", "solid.pmGp");
    model.result("pg1").feature("ptgr1").set("xdatadescr", "\u538b\u529b");
    model.result("pg1").feature("ptgr1").set("xdataunit", "kPa");
    model.result("pg1").feature("ptgr1").set("legend", true);
    model.result("pg1").feature("ptgr1").set("legendmethod", "manual");
    model.result("pg1").feature("ptgr1").setIndex("legends", "\u5927\u962a\u9ecf\u571f", 0);
    model.result("pg1").feature().duplicate("ptgr2", "ptgr1");
    model.result("pg1").run();
    model.result("pg1").feature("ptgr2").set("expr", "solid.epsm1.evoidc0");
    model.result("pg1").feature("ptgr2").set("xdataexpr", "solid.epsm1.pc0");
    model.result("pg1").feature("ptgr2").set("linecolor", "cyclereset");
    model.result("pg1").feature("ptgr2").set("linewidth", 3);
    model.result("pg1").feature("ptgr2").set("linemarker", "point");
    model.result("pg1").feature("ptgr2").set("legend", false);
    model.result("pg1").run();
    model.result("pg1").feature().duplicate("ptgr3", "ptgr1");
    model.result("pg1").run();
    model.result("pg1").feature("ptgr3")
         .set("expr", "solid.epsm1.evoidrefd-solid.epsm1.lambdaCompS*log(solid.epsm1.p/solid.epsm1.pref)");
    model.result("pg1").feature("ptgr3").set("linestyle", "dashed");
    model.result("pg1").feature("ptgr3").set("linecolor", "fromtheme");
    model.result("pg1").feature("ptgr3").setIndex("legends", "ICL", 0);
    model.result("pg1").run();
    model.result("pg1").run();
    model.result().duplicate("pg2", "pg1");
    model.result("pg2").run();
    model.result("pg2").label("\u81ea\u7136\u6ce5\u7070\u571f");
    model.result("pg2").setIndex("looplevel", new int[]{2}, 1);
    model.result("pg2").run();
    model.result("pg2").feature("ptgr1").setIndex("legends", "\u6ce5\u7070\u571f", 0);
    model.result("pg2").run();
    model.result("pg2").run();
    model.result().duplicate("pg3", "pg2");
    model.result("pg3").run();
    model.result("pg3").label("\u80f6\u7ed3\u6709\u660e\u9ecf\u571f");
    model.result("pg3").setIndex("looplevel", new int[]{3, 4, 5}, 1);
    model.result("pg3").run();
    model.result("pg3").feature("ptgr1").setIndex("legends", "\u6709\u660e\u9ecf\u571f\uff0cAw = 6%", 0);
    model.result("pg3").feature("ptgr1").setIndex("legends", "\u6709\u660e\u9ecf\u571f\uff0cAw = 9%", 1);
    model.result("pg3").feature("ptgr1").setIndex("legends", "\u6709\u660e\u9ecf\u571f\uff0cAw = 18%", 2);
    model.result("pg3").run();
    model.result("pg3").feature("ptgr3").set("data", "dset2");
    model.result("pg3").feature("ptgr3").setIndex("looplevelinput", "manual", 1);
    model.result("pg3").feature("ptgr3").setIndex("looplevel", new int[]{3}, 1);
    model.result("pg3").run();
    model.result("pg3").set("legendlayout", "outside");
    model.result("pg3").set("legendposoutside", "bottom");
    model.result("pg3").set("legendrowcount", 2);
    model.result("pg3").run();
    model.result().duplicate("pg4", "pg3");
    model.result("pg4").run();
    model.result("pg4").label("\u80f6\u7ed3\u66fc\u8c37\u9ecf\u571f");
    model.result("pg4").setIndex("looplevel", new int[]{6, 7, 8}, 1);
    model.result("pg4").run();
    model.result("pg4").feature("ptgr1").setIndex("legends", "\u66fc\u8c37\u9ecf\u571f\uff0cAw = 5%", 0);
    model.result("pg4").feature("ptgr1").setIndex("legends", "\u66fc\u8c37\u9ecf\u571f\uff0cAw = 10%", 1);
    model.result("pg4").feature("ptgr1").setIndex("legends", "\u66fc\u8c37\u9ecf\u571f\uff0cAw = 15%", 2);
    model.result("pg4").run();
    model.result("pg4").feature("ptgr3").setIndex("looplevel", new int[]{6}, 1);
    model.result("pg4").run();
    model.result("pg4").run();

    model.title("\u7ed3\u6784\u6027\u9ecf\u571f\u7684\u5404\u5411\u540c\u6027\u538b\u7f29\u8bd5\u9a8c");

    model
         .description("\u672c\u4f8b\u4f7f\u7528\u201c\u4fee\u6b63\u7684\u7ed3\u6784\u5316\u5251\u6865\u9ecf\u571f\u6a21\u578b\u201d(MSCC) \u6750\u6599\u6a21\u578b\u6765\u6a21\u62df\u5929\u7136\u7ed3\u6784\u6027\u9ecf\u571f\u548c\u4eba\u5de5\u7ed3\u6784\u6027\u9ecf\u571f\u7684\u5404\u5411\u540c\u6027\u538b\u7f29\uff0c\u76ee\u7684\u662f\u91cd\u73b0\u56db\u79cd\u7ed3\u6784\u6027\u571f\u7684\u57fa\u51c6\u4e2d\u7ed9\u51fa\u7684\u5404\u5411\u540c\u6027\u538b\u7f29\u7279\u6027\u3002");

    model.mesh().clearMeshes();

    model.sol("sol1").clearSolutionData();
    model.sol("sol2").clearSolutionData();
    model.sol("sol3").clearSolutionData();
    model.sol("sol4").clearSolutionData();
    model.sol("sol5").clearSolutionData();
    model.sol("sol6").clearSolutionData();
    model.sol("sol7").clearSolutionData();
    model.sol("sol8").clearSolutionData();
    model.sol("sol9").clearSolutionData();
    model.sol("sol10").clearSolutionData();

    model.label("isotropic_compression_mscc.mph");

    return model;
  }

  public static void main(String[] args) {
    run();
  }

}
