/*
 * elastoplastic_creep.java
 */

import com.comsol.model.*;
import com.comsol.model.util.*;

/** Model exported on Apr 30 2026, 11:28 by COMSOL 6.3.0.290. */
public class elastoplastic_creep {

  public static Model run() {
    Model model = ModelUtil.create("Model");

    model
         .modelPath("D:\\Program Files\\COMSOL\\COMSOL63\\Multiphysics\\applications\\Nonlinear_Structural_Materials_Module\\Creep");

    model.component().create("comp1", true);

    model.component("comp1").geom().create("geom1", 2);
    model.component("comp1").geom("geom1").axisymmetric(true);

    model.component("comp1").mesh().create("mesh1");

    model.component("comp1").physics().create("solid", "SolidMechanics", "geom1");

    model.study().create("std1");
    model.study("std1").create("time", "Transient");
    model.study("std1").feature("time").setSolveFor("/physics/solid", true);

    model.param().set("m", "0.9");
    model.param().descr("m", "\u5e42\u5f8b\u6307\u6570");

    model.component("comp1").func().create("int1", "Interpolation");
    model.component("comp1").func("int1").set("funcname", "pressure");
    model.component("comp1").func("int1").setIndex("table", 0, 0, 0);
    model.component("comp1").func("int1").setIndex("table", 0, 0, 1);
    model.component("comp1").func("int1").setIndex("table", 1, 1, 0);
    model.component("comp1").func("int1").setIndex("table", 0, 1, 1);
    model.component("comp1").func("int1").setIndex("table", 2, 2, 0);
    model.component("comp1").func("int1").setIndex("table", 3600, 2, 1);
    model.component("comp1").func("int1").setIndex("table", 999, 3, 0);
    model.component("comp1").func("int1").setIndex("table", 3600, 3, 1);
    model.component("comp1").func("int1").setIndex("table", 1000, 4, 0);
    model.component("comp1").func("int1").setIndex("table", -3600, 4, 1);
    model.component("comp1").func("int1").setIndex("table", 3000, 5, 0);
    model.component("comp1").func("int1").setIndex("table", -3600, 5, 1);
    model.component("comp1").func("int1").setIndex("argunit", "s", 0);
    model.component("comp1").func("int1").setIndex("fununit", "Pa", 0);
    model.component("comp1").func().create("an1", "Analytic");
    model.component("comp1").func("an1").set("funcname", "T");
    model.component("comp1").func("an1")
         .set("expr", "500*t^((r-7e-2)/9e-2)*(t<=0.1)+50*t^((r-0.16)/9e-2)*(t>0.1)*(t<=1)+50*(t>1)");
    model.component("comp1").func("an1").set("args", "t, r");
    model.component("comp1").func("an1").setIndex("argunit", "s", 0);
    model.component("comp1").func("an1").setIndex("argunit", "m", 1);
    model.component("comp1").func("an1").set("fununit", "K");
    model.component("comp1").func().create("int2", "Interpolation");
    model.component("comp1").func("int2").set("source", "file");
    model.component("comp1").func("int2").set("filename", "elastoplastic_creep_target.txt");
    model.component("comp1").func("int2").importData();
    model.component("comp1").func("int2").setEntry("columnType", "col2", "value");
    model.component("comp1").func("int2").setEntry("columnType", "col3", "value");
    model.component("comp1").func("int2").setEntry("columnType", "col5", "value");
    model.component("comp1").func("int2").setEntry("columnType", "col6", "value");
    model.component("comp1").func("int2").setEntry("funcnames", "col2", "sz_target");
    model.component("comp1").func("int2").setEntry("funcnames", "col3", "sphi_target");
    model.component("comp1").func("int2").setEntry("funcnames", "col4", "mises_target");
    model.component("comp1").func("int2").setEntry("funcnames", "col5", "epe_target");
    model.component("comp1").func("int2").setEntry("funcnames", "col6", "ece_target");

    model.component("comp1").geom("geom1").create("r1", "Rectangle");
    model.component("comp1").geom("geom1").feature("r1").set("size", new String[]{"9e-2", "9e-3"});
    model.component("comp1").geom("geom1").feature("r1").set("pos", new double[]{0.16, 0});
    model.component("comp1").geom("geom1").runPre("fin");
    model.component("comp1").geom("geom1").run();

    model.component("comp1").physics("solid").prop("StructuralTransientBehavior")
         .set("StructuralTransientBehavior", "Quasistatic");
    model.component("comp1").physics("solid").feature("lemm1").create("te1", "ThermalExpansion", 2);
    model.component("comp1").physics("solid").feature("lemm1").feature("te1")
         .set("minput_temperature_src", "userdef");
    model.component("comp1").physics("solid").feature("lemm1").feature("te1").set("minput_temperature", "T(t,R)");

    model.common("cminpt").set("modified", new String[][]{{"strainreferencetemperature", "0"}});

    model.component("comp1").physics("solid").feature("lemm1").create("plsty1", "Plasticity", 2);
    model.component("comp1").physics("solid").feature("lemm1").feature("plsty1")
         .set("IsotropicHardeningModel", "HardeningFunction");
    model.component("comp1").physics("solid").feature("lemm1").create("cmm1", "Creep2", 2);
    model.component("comp1").physics("solid").feature("lemm1").feature("cmm1").set("A_nor_mat", "userdef");
    model.component("comp1").physics("solid").feature("lemm1").feature("cmm1").set("A_nor", "1e-26/m*(t>2)");
    model.component("comp1").physics("solid").feature("lemm1").feature("cmm1").set("sigRef_nor_mat", "userdef");
    model.component("comp1").physics("solid").feature("lemm1").feature("cmm1").set("sigRef_nor", 1);
    model.component("comp1").physics("solid").feature("lemm1").feature("cmm1").set("n_nor_mat", "userdef");
    model.component("comp1").physics("solid").feature("lemm1").feature("cmm1").set("n_nor", 5.25);
    model.component("comp1").physics("solid").feature("lemm1").feature("cmm1").set("isotropicHardening", "time");
    model.component("comp1").physics("solid").feature("lemm1").feature("cmm1").set("m", "m");
    model.component("comp1").physics("solid").feature("lemm1").feature("cmm1").set("tShift", "1e-3");
    model.component("comp1").physics("solid").feature("lemm1").feature("cmm1").set("tRef", 1);
    model.component("comp1").physics("solid").feature("lemm1").feature("cmm1").set("timeMethod", "forwardEuler");
    model.component("comp1").physics("solid").feature("lemm1").set("CalculateDissipatedEnergy", true);
    model.component("comp1").physics("solid").create("bndl1", "BoundaryLoad", 1);
    model.component("comp1").physics("solid").feature("bndl1").selection().set(1);
    model.component("comp1").physics("solid").feature("bndl1")
         .set("forceReferenceArea", new String[]{"pressure(t)", "0", "0"});
    model.component("comp1").physics("solid").create("disp1", "Displacement2", 2);
    model.component("comp1").physics("solid").feature("disp1").selection().set(1);
    model.component("comp1").physics("solid").feature("disp1").setIndex("Direction", "prescribed", 2);

    model.component("comp1").material().create("mat1", "Common");
    model.component("comp1").material("mat1").propertyGroup()
         .create("Enu", "Enu", "Young's_modulus_and_Poisson's_ratio");
    model.component("comp1").material("mat1").propertyGroup("Enu").set("E", new String[]{"22[MPa]"});
    model.component("comp1").material("mat1").propertyGroup("Enu").set("nu", new String[]{"0.3"});
    model.component("comp1").material("mat1").propertyGroup("def").set("density", new String[]{"0"});
    model.component("comp1").material("mat1").propertyGroup("def")
         .set("thermalexpansioncoefficient", new String[]{"18.5e-6"});
    model.component("comp1").material("mat1").propertyGroup()
         .create("ElastoplasticModel", "ElastoplasticModel", "Elastoplastic_material_model");
    model.component("comp1").material("mat1").propertyGroup("ElastoplasticModel")
         .set("sigmags", new String[]{"sigma_yield(0)"});
    model.component("comp1").material("mat1").propertyGroup("ElastoplasticModel")
         .set("sigmagh", new String[]{"sigma_yield(epe)-sigma_yield(0)"});
    model.component("comp1").material("mat1").propertyGroup("ElastoplasticModel").addInput("effectiveplasticstrain");
    model.component("comp1").material("mat1").propertyGroup("ElastoplasticModel").func()
         .create("int1", "Interpolation");
    model.component("comp1").material("mat1").propertyGroup("ElastoplasticModel").func("int1")
         .set("funcname", "sigma_yield");
    model.component("comp1").material("mat1").propertyGroup("ElastoplasticModel").func("int1")
         .setIndex("table", 0, 0, 0);
    model.component("comp1").material("mat1").propertyGroup("ElastoplasticModel").func("int1")
         .setIndex("table", 9900, 0, 1);
    model.component("comp1").material("mat1").propertyGroup("ElastoplasticModel").func("int1")
         .setIndex("table", "3.9e-4", 1, 0);
    model.component("comp1").material("mat1").propertyGroup("ElastoplasticModel").func("int1")
         .setIndex("table", 12500, 1, 1);
    model.component("comp1").material("mat1").propertyGroup("ElastoplasticModel").func("int1")
         .setIndex("table", "9.5e-4", 2, 0);
    model.component("comp1").material("mat1").propertyGroup("ElastoplasticModel").func("int1")
         .setIndex("table", 15200, 2, 1);
    model.component("comp1").material("mat1").propertyGroup("ElastoplasticModel").func("int1")
         .setIndex("table", "2.95e-3", 3, 0);
    model.component("comp1").material("mat1").propertyGroup("ElastoplasticModel").func("int1")
         .setIndex("table", 17500, 3, 1);
    model.component("comp1").material("mat1").propertyGroup("ElastoplasticModel").func("int1")
         .setIndex("table", "6.15e-3", 4, 0);
    model.component("comp1").material("mat1").propertyGroup("ElastoplasticModel").func("int1")
         .setIndex("table", 20000, 4, 1);
    model.component("comp1").material("mat1").propertyGroup("ElastoplasticModel").func("int1")
         .setIndex("argunit", 1, 0);
    model.component("comp1").material("mat1").propertyGroup("ElastoplasticModel").func("int1")
         .setIndex("fununit", "Pa", 0);

    model.component("comp1").mesh("mesh1").create("map1", "Map");
    model.component("comp1").mesh("mesh1").feature("map1").create("dis1", "Distribution");
    model.component("comp1").mesh("mesh1").feature("map1").feature("dis1").selection().set(1);
    model.component("comp1").mesh("mesh1").feature("map1").feature("dis1").set("numelem", 1);
    model.component("comp1").mesh("mesh1").feature("map1").create("dis2", "Distribution");
    model.component("comp1").mesh("mesh1").feature("map1").feature("dis2").selection().set(2);
    model.component("comp1").mesh("mesh1").feature("map1").feature("dis2").set("numelem", 10);
    model.component("comp1").mesh("mesh1").run();

    model.study("std1").feature("time")
         .set("tlist", "0 0.01 0.06 0.1 range(0.4, 0.2, 2) 10 50 100 200 500 range(999, 0.2, 1000) 1002 1004 1010 1025 1050 1100 1160 range(1200, 100, 1500) 2000 3000");
    model.study("std1").showAutoSequences("all");

    model.sol("sol1").feature("t1").set("tstepsbdf", "strict");
    model.sol("sol1").feature("t1").set("initialstepbdfactive", true);
    model.sol("sol1").feature("t1").set("initialstepbdf", 0.01);

    model.study("std1").createAutoSequences("all");

    model.sol("sol1").runAll();

    model.result().create("pg1", "PlotGroup2D");
    model.result("pg1").set("data", "dset1");
    model.result("pg1").setIndex("looplevel", 37, 0);
    model.result("pg1").label("\u5e94\u529b (solid)");
    model.result("pg1").set("frametype", "spatial");
    model.result("pg1").create("surf1", "Surface");
    model.result("pg1").feature("surf1").set("expr", new String[]{"solid.misesGp"});
    model.result("pg1").feature("surf1").set("threshold", "manual");
    model.result("pg1").feature("surf1").set("thresholdvalue", 0.2);
    model.result("pg1").feature("surf1").set("colortable", "Rainbow");
    model.result("pg1").feature("surf1").set("colortabletrans", "none");
    model.result("pg1").feature("surf1").set("colorscalemode", "linear");
    model.result("pg1").feature("surf1").set("resolution", "normal");
    model.result("pg1").feature("surf1").set("colortable", "Prism");
    model.result("pg1").feature("surf1").create("def", "Deform");
    model.result("pg1").feature("surf1").feature("def").set("expr", new String[]{"u", "w"});
    model.result("pg1").feature("surf1").feature("def").set("descr", "\u4f4d\u79fb\u573a");
    model.result().dataset().create("dset1solidrev", "Revolve2D");
    model.result().dataset("dset1solidrev").set("data", "dset1");
    model.result().dataset("dset1solidrev").set("revangle", 225);
    model.result().dataset("dset1solidrev").set("startangle", -90);
    model.result().dataset("dset1solidrev").set("hasspacevars", true);
    model.result().create("pg2", "PlotGroup3D");
    model.result("pg2").set("data", "dset1solidrev");
    model.result("pg2").setIndex("looplevel", 37, 0);
    model.result("pg2").label("\u5e94\u529b, 3D (solid)");
    model.result("pg2").set("frametype", "spatial");
    model.result("pg2").create("surf1", "Surface");
    model.result("pg2").feature("surf1").set("expr", new String[]{"solid.misesGp"});
    model.result("pg2").feature("surf1").set("threshold", "manual");
    model.result("pg2").feature("surf1").set("thresholdvalue", 0.2);
    model.result("pg2").feature("surf1").set("colortable", "Rainbow");
    model.result("pg2").feature("surf1").set("colortabletrans", "none");
    model.result("pg2").feature("surf1").set("colorscalemode", "linear");
    model.result("pg2").feature("surf1").set("colortable", "Prism");
    model.result("pg2").feature("surf1").create("def", "Deform");
    model.result().dataset("dset1solidrev").set("hasspacevars", true);
    model.result("pg2").feature("surf1").feature("def").set("revcoordsys", "cylindrical");
    model.result("pg2").feature("surf1").feature("def").set("expr", new String[]{"u", "v", "w"});
    model.result("pg2").feature("surf1").feature("def").set("descr", "\u4f4d\u79fb\u573a");
    model.result("pg2").feature("surf1").feature("def").set("descractive", true);
    model.result("pg1").run();
    model.result().dataset().create("cpt1", "CutPoint2D");
    model.result().dataset("cpt1").set("pointx", "0.16 0.205 0.25");
    model.result().dataset("cpt1").set("pointy", "5e-3");
    model.result().create("pg3", "PlotGroup1D");
    model.result("pg3").run();
    model.result("pg3").label("\u7b49\u6548\u5851\u6027\u5e94\u53d8");
    model.result("pg3").set("legendpos", "upperleft");
    model.result("pg3").set("data", "cpt1");
    model.result("pg3").set("titletype", "label");
    model.result("pg3").create("ptgr1", "PointGraph");
    model.result("pg3").feature("ptgr1").set("markerpos", "datapoints");
    model.result("pg3").feature("ptgr1").set("linewidth", "preference");
    model.result("pg3").feature("ptgr1").set("expr", "solid.epeGp");
    model.result("pg3").feature("ptgr1").set("descr", "\u7b49\u6548\u5851\u6027\u5e94\u53d8");
    model.result("pg3").feature("ptgr1").set("linewidth", 2);
    model.result("pg3").feature("ptgr1").set("legend", true);
    model.result("pg3").feature("ptgr1").set("legendmethod", "manual");
    model.result("pg3").feature("ptgr1").setIndex("legends", "\u8ba1\u7b97\u503c\uff0c\u5185\u8868\u9762", 0);
    model.result("pg3").feature("ptgr1")
         .setIndex("legends", "\u8ba1\u7b97\u503c\uff0c\u4e2d\u95f4\u539a\u5ea6\u4f4d\u7f6e", 1);
    model.result("pg3").feature("ptgr1").setIndex("legends", "\u8ba1\u7b97\u503c\uff0c\u5916\u8868\u9762", 2);
    model.result("pg3").run();
    model.result("pg3").create("glob1", "Global");
    model.result("pg3").feature("glob1").set("markerpos", "datapoints");
    model.result("pg3").feature("glob1").set("linewidth", "preference");
    model.result("pg3").feature("glob1").setIndex("expr", "epe_target(t)", 0);
    model.result("pg3").feature("glob1").setIndex("descr", "", 0);
    model.result("pg3").feature("glob1").set("linestyle", "none");
    model.result("pg3").feature("glob1").set("linemarker", "circle");
    model.result("pg3").feature("glob1").set("linecolor", "fromtheme");
    model.result("pg3").feature("glob1").set("legendmethod", "manual");
    model.result("pg3").feature("glob1").setIndex("legends", "\u76ee\u6807\uff0c\u5185\u8868\u9762", 0);
    model.result("pg3").run();
    model.result("pg3").run();
    model.result("pg3").set("axislimits", true);
    model.result("pg3").set("xmin", 0);
    model.result("pg3").set("xmax", 2);
    model.result("pg3").run();
    model.result().duplicate("pg4", "pg3");
    model.result("pg4").run();
    model.result("pg4").label("\u7b49\u6548\u8815\u53d8\u5e94\u53d8");
    model.result("pg4").set("axislimits", false);
    model.result("pg4").run();
    model.result("pg4").feature("ptgr1").set("expr", "solid.eceGp");
    model.result("pg4").feature("ptgr1").set("descr", "\u7b49\u6548\u8815\u53d8\u5e94\u53d8");
    model.result("pg4").run();
    model.result("pg4").feature("glob1").setIndex("expr", "ece_target(t)", 0);
    model.result("pg4").run();
    model.result().dataset().create("cpt2", "CutPoint2D");
    model.result().dataset("cpt2").set("pointx", 0.16);
    model.result().dataset("cpt2").set("pointy", "5e-3");
    model.result("pg4").run();
    model.result().duplicate("pg5", "pg4");
    model.result("pg5").run();
    model.result("pg5").label("\u8f74\u5411\u5e94\u529b");
    model.result("pg5").set("data", "cpt2");
    model.result("pg5").set("legendpos", "upperright");
    model.result("pg5").run();
    model.result("pg5").feature("ptgr1").set("expr", "solid.sGpzz");
    model.result("pg5").feature("ptgr1").set("descr", "\u5e94\u529b\u5f20\u91cf\uff0czz \u5206\u91cf");
    model.result("pg5").feature("ptgr1").setIndex("legends", "\u8ba1\u7b97\u503c", 0);
    model.result("pg5").run();
    model.result("pg5").feature("glob1").setIndex("expr", "sz_target(t)", 0);
    model.result("pg5").feature("glob1").setIndex("legends", "\u76ee\u6807", 0);
    model.result("pg5").run();
    model.result("pg5").run();
    model.result().duplicate("pg6", "pg5");
    model.result("pg6").run();
    model.result("pg6").label("\u73af\u5411\u5e94\u529b");
    model.result("pg6").run();
    model.result("pg6").feature("ptgr1").set("expr", "solid.sGpphiphi");
    model.result("pg6").feature("ptgr1").set("descr", "\u5e94\u529b\u5f20\u91cf\uff0cphiphi \u5206\u91cf");
    model.result("pg6").run();
    model.result("pg6").feature("glob1").setIndex("expr", "sphi_target(t)", 0);
    model.result("pg6").run();
    model.result("pg6").run();
    model.result().duplicate("pg7", "pg6");
    model.result("pg7").run();
    model.result("pg7").label("von Mises \u5e94\u529b");
    model.result("pg7").run();
    model.result("pg7").feature("ptgr1").set("expr", "solid.misesGp");
    model.result("pg7").feature("ptgr1").set("descr", "von Mises \u5e94\u529b");
    model.result("pg7").run();
    model.result("pg7").feature("glob1").setIndex("expr", "mises_target(t)", 0);
    model.result("pg7").run();
    model.result().create("pg8", "PlotGroup1D");
    model.result("pg8").run();
    model.result("pg8").label("\u603b\u80fd\u91cf");
    model.result("pg8").set("titletype", "label");
    model.result("pg8").create("glob1", "Global");
    model.result("pg8").feature("glob1").set("markerpos", "datapoints");
    model.result("pg8").feature("glob1").set("linewidth", "preference");
    model.result("pg8").feature("glob1").set("expr", new String[]{"solid.Ws_tot"});
    model.result("pg8").feature("glob1").set("descr", new String[]{"\u603b\u5f39\u6027\u5e94\u53d8\u80fd"});
    model.result("pg8").feature("glob1").set("expr", new String[]{"solid.Ws_tot", "solid.Wp_tot"});
    model.result("pg8").feature("glob1")
         .set("descr", new String[]{"\u603b\u5f39\u6027\u5e94\u53d8\u80fd", "\u603b\u5851\u6027\u8017\u6563"});
    model.result("pg8").feature("glob1").set("expr", new String[]{"solid.Ws_tot", "solid.Wp_tot", "solid.Wc_tot"});
    model.result("pg8").feature("glob1")
         .set("descr", new String[]{"\u603b\u5f39\u6027\u5e94\u53d8\u80fd", "\u603b\u5851\u6027\u8017\u6563", "\u603b\u8815\u53d8\u8017\u6563"});
    model.result("pg8").run();
    model.result("pg8").set("legendpos", "upperleft");
    model.result("pg8").run();
    model.result("pg7").run();

    model.title("\u7ec4\u5408\u5f39\u5851\u6027\u548c\u8815\u53d8\u6750\u6599\u6a21\u578b");

    model
         .description("\u672c\u4f8b\u6f14\u793a\u5982\u4f55\u5bf9\u5305\u542b\u8815\u53d8\u548c\u5f39\u5851\u6027\u7684\u975e\u7ebf\u6027\u6750\u6599\u6a21\u578b\u6267\u884c\u5e94\u529b\u5206\u6790\u3002\u8fd9\u662f\u4e00\u4e2a NAFEMS \u57fa\u51c6\u6a21\u578b\u3002");

    model.label("elastoplastic_creep.mph");

    return model;
  }

  public static void main(String[] args) {
    run();
  }

}
