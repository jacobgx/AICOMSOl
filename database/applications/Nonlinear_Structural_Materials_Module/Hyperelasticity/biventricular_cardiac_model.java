/*
 * biventricular_cardiac_model.java
 */

import com.comsol.model.*;
import com.comsol.model.util.*;

/** Model exported on Apr 30 2026, 11:30 by COMSOL 6.3.0.290. */
public class biventricular_cardiac_model {

  public static Model run() {
    Model model = ModelUtil.create("Model");

    model
         .modelPath("D:\\Program Files\\COMSOL\\COMSOL63\\Multiphysics\\applications\\Nonlinear_Structural_Materials_Module\\Hyperelasticity");

    model.component().create("comp1", true);

    model.component("comp1").geom().create("geom1", 3);
    model.component("comp1").geom("geom1").geomRep("comsol");

    model.component("comp1").mesh().create("mesh1");
    model.component("comp1").mesh("mesh1").contribute("geom/detail", true);

    model.component("comp1").physics().create("wd", "WallDistance", "geom1");
    model.component("comp1").physics().create("wd2", "WallDistance", "geom1");
    model.component("comp1").physics().create("cc", "CurvilinearCoordinates", "geom1");

    model.study().create("std1");
    model.study("std1").create("stat", "Stationary");
    model.study("std1").feature("stat").setSolveFor("/physics/wd", true);
    model.study("std1").feature("stat").setSolveFor("/physics/wd2", true);
    model.study("std1").feature("stat").setSolveFor("/physics/cc", true);

    model.component("comp1").geom("geom1").insertFile("biventricular_cardiac_model_geom_sequence.mph", "geom1");
    model.component("comp1").geom("geom1").run("igf1");

    model.param().label("\u5fc3\u810f\u51e0\u4f55\u53c2\u6570");

    model.component("comp1").selection().create("sel1", "Explicit");
    model.component("comp1").selection("sel1").label("\u57fa\u9762");
    model.component("comp1").selection("sel1").geom(2);

    model.component("comp1").view("view1").set("transparency", true);

    model.component("comp1").selection("sel1").set(1, 3, 9, 17);

    model.component("comp1").view("view1").set("showgrid", true);

    model.component("comp1").selection().create("sel2", "Explicit");
    model.component("comp1").selection("sel2").label("LV-\u5fc3\u5185\u819c");
    model.component("comp1").selection("sel2").geom(2);
    model.component("comp1").selection("sel2").set(6, 7, 12, 16);

    model.component("comp1").view("view1").set("showgrid", true);

    model.component("comp1").selection().create("sel3", "Explicit");
    model.component("comp1").selection("sel3").label("RV-\u5fc3\u5185\u819c");
    model.component("comp1").selection("sel3").geom(2);
    model.component("comp1").selection("sel3").set(20, 22);

    model.component("comp1").view("view1").set("showgrid", true);

    model.component("comp1").selection().create("sel4", "Explicit");
    model.component("comp1").selection("sel4").label("\u5fc3\u5916\u819c");
    model.component("comp1").selection("sel4").geom(2);
    model.component("comp1").selection("sel4").set(4, 5, 10, 11, 15, 18, 19, 21);

    model.component("comp1").view("view1").set("showgrid", true);

    model.component("comp1").selection().create("uni1", "Union");
    model.component("comp1").selection("uni1").label("\u5fc3\u5185\u819c");
    model.component("comp1").selection("uni1").set("entitydim", 2);
    model.component("comp1").selection("uni1").set("input", new String[]{"sel2", "sel3"});

    model.component("comp1").view("view1").set("showgrid", true);

    model.param().create("par2");
    model.param("par2").label("\u7ed3\u6784\u529b\u5b66\u53c2\u6570");

//    To import content from file, use:
//    model.param("par2").loadFile("FILENAME");
    model.param("par2").set("mu_lame", "0.2[MPa]", "Lam\u00e9 \u53c2\u6570");
    model.param("par2").set("lambda_lame", "0.5[MPa]", "Lam\u00e9 \u53c2\u6570");
    model.param("par2").set("rhos", "1370[kg/m^3]", "\u5fc3\u808c\u5bc6\u5ea6");
    model.param("par2").set("af", "1.685[kPa]", "\u7ea4\u7ef4\u65b9\u5411\u7684\u6750\u6599\u5c5e\u6027");
    model.param("par2").set("bf", "15.779", "\u7ea4\u7ef4\u65b9\u5411\u7684\u6750\u6599\u5c5e\u6027");
    model.param("par2")
         .set("theta_epi_max", "-60 [deg]", "\u57fa\u5e95\u9762\u7684\u5fc3\u5916\u819c\u7ea4\u7ef4\u89d2");
    model.param("par2")
         .set("theta_endo_max", "+60 [deg]", "\u57fa\u5e95\u9762\u7684\u5fc3\u5185\u819c\u7ea4\u7ef4\u89d2");
    model.param().create("par3");
    model.param("par3").label("\u7535\u53c2\u6570");

//    To import content from file, use:
//    model.param("par3").loadFile("FILENAME");
    model.param("par3").set("gamma", "0.002", "\u6062\u590d\u53d8\u91cf\u53c2\u6570");
    model.param("par3").set("mu1", "0.2", "\u6062\u590d\u53d8\u91cf\u53c2\u6570");
    model.param("par3").set("mu2", "0.3", "\u6062\u590d\u53d8\u91cf\u53c2\u6570");
    model.param("par3").set("b", "0.15", "Aliev-Panfilov \u53c2\u6570");
    model.param("par3").set("alpha", "0.01", "Aliev-Panfilov \u53c2\u6570");
    model.param("par3").set("c", "8", "Aliev-Panfilov \u53c2\u6570");
    model.param("par3")
         .set("Gs", "10", "\u6700\u5927\u7535\u5bfc\u7387\uff0c\u62c9\u4f38\u8bf1\u53d1\u7684\u6fc0\u52b1");
    model.param("par3")
         .set("phis", "0.6", "\u62c9\u4f38\u6fc0\u6d3b\u7684\u901a\u9053\u7684\u9759\u606f\u7535\u4f4d");
    model.param("par3").set("d_iso", "1[mm^2/ms]", "\u5404\u5411\u540c\u6027\u4f20\u5bfc\u901f\u5ea6");
    model.param("par3").set("d_ani", "0.1[mm^2/ms]", "\u5404\u5411\u5f02\u6027\u4f20\u5bfc\u901f\u5ea6");
    model.param("par3").set("Cm", "1[F/m^2]", "\u5355\u4f4d\u9762\u79ef\u7684\u8de8\u819c\u7535\u5bb9");
    model.param("par3").set("Chi_m", "1[1/m]", "\u9762\u79ef\u4e0e\u4f53\u79ef\u6bd4");
    model.param().create("par4");
    model.param("par4").label("\u4e3b\u5e94\u529b\u53c2\u6570");

//    To import content from file, use:
//    model.param("par4").loadFile("FILENAME");
    model.param("par4").set("kT", "0.005[MPa/mV]", "\u9971\u548c\u4e3b\u52a8\u5e94\u529b");
    model.param("par4").set("Phir", "-80[mV]", "\u9759\u606f\u7535\u4f4d");
    model.param("par4").set("e0", "0.1[1/ms]", "\u6536\u7f29\u901f\u7387\u5e38\u6570");
    model.param("par4").set("einf", "1[1/ms]", "\u6536\u7f29\u901f\u7387\u5e38\u6570");
    model.param("par4").set("zeta", "1[1/mV]", "\u8dc3\u8fc1\u7387");
    model.param("par4").set("Phibar", "0[mV]", "\u76f8\u79fb");
    model.param("par4").set("vf", "1", "\u6cbf\u7ea4\u7ef4\u65b9\u5411\u7684\u4e3b\u52a8\u5e94\u529b\u7f29\u653e");
    model.param("par4").set("vs", "0.4", "\u6cbf\u7247\u65b9\u5411\u7684\u4e3b\u52a8\u5e94\u529b\u7f29\u653e");
    model.param("par4").set("vn", "0.4", "\u6cbf\u7247\u6cd5\u5411\u7684\u4e3b\u52a8\u5e94\u529b\u7f29\u653e");
    model.param().create("par5");
    model.param("par5").label("\u8f6c\u6362\u7cfb\u6570\u53c2\u6570");

//    To import content from file, use:
//    model.param("par5").loadFile("FILENAME");
    model.param("par5").set("betaPhi", "100[mV]", "\u8f6c\u6362\u56e0\u5b50");
    model.param("par5").set("deltaPhi", "-80[mV]", "\u8f6c\u6362\u56e0\u5b50");
    model.param("par5").set("t0", "12[ms]", "\u65f6\u95f4\u53c2\u6570");
    model.param("par5").set("t1", "75[ms]", "\u65f6\u95f4\u53c2\u6570");
    model.param("par5").set("tau0", "0.55", "\u65f6\u95f4\u53c2\u6570");
    model.param("par5").set("ts", "12.9[ms]", "\u65f6\u95f4\u53c2\u6570");

    model.component("comp1").physics("wd").label("\u58c1\u8ddd\u79bb\uff1a\u5fc3\u5916\u819c");
    model.component("comp1").physics("wd").create("wall1", "Wall", 2);
    model.component("comp1").physics("wd").feature("wall1").selection().named("sel4");
    model.component("comp1").physics("wd2").label("\u58c1\u8ddd\u79bb\uff1a\u5fc3\u5185\u819c");
    model.component("comp1").physics("wd2").create("wall1", "Wall", 2);
    model.component("comp1").physics("wd2").feature("wall1").selection().named("uni1");
    model.component("comp1").physics("cc").label("\u8584\u7247\u65b9\u5411");
    model.component("comp1").physics("cc").prop("Settings").set("CreateBasis", true);
    model.component("comp1").physics("cc").feature("css1").set("SecondVector", "z");
    model.component("comp1").physics("cc").create("diff1", "DiffusionMethod", 3);
    model.component("comp1").physics("cc").feature("diff1").create("inl1", "Inlet", 2);
    model.component("comp1").physics("cc").feature("diff1").feature("inl1").selection().named("uni1");
    model.component("comp1").physics("cc").feature("diff1").create("out1", "Outlet", 2);
    model.component("comp1").physics("cc").feature("diff1").feature("out1").selection().named("sel4");

    model.component("comp1").mesh("mesh1").autoMeshSize(3);
    model.component("comp1").mesh("mesh1").run();

    model.study("std1").label("\u7814\u7a76\uff1a\u7ea4\u7ef4\u65b9\u5411");
    model.study("std1").createAutoSequences("all");

    model.sol("sol1").runAll();

    model.result().create("pg1", "PlotGroup3D");
    model.result("pg1").set("data", "dset1");
    model.result("pg1").create("slc1", "Slice");
    model.result("pg1").feature("slc1").set("expr", "G");
    model.result("pg1").feature("slc1").set("descr", "\u58c1\u8ddd\u79bb\u5012\u6570");
    model.result().create("pg2", "PlotGroup3D");
    model.result("pg2").set("data", "dset1");
    model.result("pg2").create("slc1", "Slice");
    model.result("pg2").feature("slc1").set("expr", "G2");
    model.result("pg2").feature("slc1").set("descr", "\u58c1\u8ddd\u79bb\u5012\u6570");
    model.result().create("pg3", "PlotGroup3D");
    model.result("pg3").label("\u77e2\u91cf\u573a (cc)");
    model.result("pg3").feature().create("str1", "Streamline");
    model.result("pg3").feature("str1").set("showsolutionparams", "on");
    model.result("pg3").feature("str1").set("posmethod", "uniform");
    model.result("pg3").feature("str1").set("udist", 0.1);
    model.result("pg3").feature("str1").set("linetype", "tube");
    model.result("pg3").feature("str1").set("smooth", "internal");
    model.result("pg3").feature("str1").set("maxlen", Double.POSITIVE_INFINITY);
    model.result("pg3").feature("str1").set("maxtime", Double.POSITIVE_INFINITY);
    model.result("pg3").feature("str1").set("showsolutionparams", "on");
    model.result("pg3").feature("str1").set("maxlen", Double.POSITIVE_INFINITY);
    model.result("pg3").feature("str1").set("maxtime", Double.POSITIVE_INFINITY);
    model.result("pg3").feature("str1").set("showsolutionparams", "on");
    model.result("pg3").feature("str1").set("maxlen", Double.POSITIVE_INFINITY);
    model.result("pg3").feature("str1").set("maxtime", Double.POSITIVE_INFINITY);
    model.result("pg3").feature("str1").set("showsolutionparams", "on");
    model.result("pg3").feature("str1").set("maxlen", Double.POSITIVE_INFINITY);
    model.result("pg3").feature("str1").set("maxtime", Double.POSITIVE_INFINITY);
    model.result("pg3").feature("str1").set("showsolutionparams", "on");
    model.result("pg3").feature("str1").set("maxlen", Double.POSITIVE_INFINITY);
    model.result("pg3").feature("str1").set("maxtime", Double.POSITIVE_INFINITY);
    model.result("pg3").feature("str1").set("data", "parent");
    model.result().create("pg4", "PlotGroup3D");
    model.result("pg4").set("data", "dset1");
    model.result("pg4").label("\u5750\u6807\u7cfb (cc)");
    model.result("pg4").create("sys1", "CoordSysVolume");
    model.result("pg4").feature("sys1").set("sys", "cc_cs");
    model.result("pg1").run();
    model.result("pg1").label("\u58c1\u8ddd\u79bb\uff1a\u5fc3\u5916\u819c");
    model.result("pg2").run();
    model.result("pg2").label("\u58c1\u8ddd\u79bb\uff1a\u5fc3\u5185\u819c");
    model.result("pg1").run();

    model.nodeGroup().create("grp1", "Results");
    model.nodeGroup("grp1").set("type", "plotgroup");
    model.nodeGroup("grp1").add("plotgroup", "pg1");
    model.nodeGroup("grp1").add("plotgroup", "pg2");
    model.nodeGroup("grp1").add("plotgroup", "pg3");
    model.nodeGroup("grp1").add("plotgroup", "pg4");
    model.nodeGroup("grp1").label("\u7814\u7a76\uff1a\u7ea4\u7ef4\u65b9\u5411");

    model.result("pg4").run();
    model.result("pg4").run();
    model.result("pg4").feature("sys1").set("znumber", 4);
    model.result("pg4").run();
    model.result("pg4").feature("sys1").set("ynumber", 4);
    model.result("pg4").run();
    model.result("pg4").feature("sys1").set("xnumber", 4);
    model.result("pg4").run();

    model.component("comp1").view("view1").set("showgrid", true);

    model.component("comp1").variable().create("var1");
    model.component("comp1").variable("var1").label("\u7ea4\u7ef4\u65b9\u5411");

//    To import content from file, use:
//    model.component("comp1").variable("var1").loadFile("FILENAME");
    model.component("comp1").variable("var1")
         .set("D_Epi", "wd.Dw", "\u4e0e\u5916\u8fb9\u754c\u7684\u8ddd\u79bb\uff08\u5fc3\u5916\u819c\uff09");
    model.component("comp1").variable("var1")
         .set("D_Endo", "wd2.Dw", "\u4e0e\u5185\u8fb9\u754c\u7684\u8ddd\u79bb\uff08\u5fc3\u5185\u819c\uff09");
    model.component("comp1").variable("var1")
         .set("Beta", "if(D_Endo+D_Epi>0,D_Epi/(D_Endo+D_Epi),0.5)", "\u65e0\u91cf\u7eb2\u58c1\u8ddd\u79bb");
    model.component("comp1").variable("var1")
         .set("theta_endo", "theta_endo_max+(Z/cL)*(theta_endo_max)", "\u5fc3\u5185\u819c\u4e0a\u7684\u7ea4\u7ef4\u65b9\u5411");
    model.component("comp1").variable("var1")
         .set("theta_epi", "theta_epi_max+(Z/cR)*(theta_epi_max)", "\u5fc3\u5916\u819c\u4e0a\u7684\u7ea4\u7ef4\u65b9\u5411");
    model.component("comp1").variable("var1")
         .set("theta", "(theta_epi*(1-Beta)+theta_endo*Beta)", "\u5fc3\u808c\u4e2d\u7684\u7ea4\u7ef4\u65b9\u5411");

    model.component("comp1").coordSystem().create("sys2", "Rotated");
    model.component("comp1").coordSystem("sys2").set("angle", new String[]{"0", "theta", "0"});
    model.component("comp1").coordSystem().create("sys3", "Composite");
    model.component("comp1").coordSystem("sys3").label("\u7ea4\u7ef4\u53c2\u8003\u7cfb");
    model.component("comp1").coordSystem("sys3").set("baseSystem", "cc_cs");
    model.component("comp1").coordSystem("sys3").set("relativeSystem", "sys2");
    model.component("comp1").coordSystem().create("sys4", "Cylindrical");
    model.component("comp1").coordSystem("sys4").set("frametype", "material");

    model.nodeGroup().create("grp2", "Definitions", "comp1");
    model.nodeGroup("grp2").set("type", "coordsys");
    model.nodeGroup("grp2").add("coordsys", "sys1");
    model.nodeGroup("grp2").add("coordsys", "cc_cs");
    model.nodeGroup("grp2").add("coordsys", "sys2");
    model.nodeGroup("grp2").add("coordsys", "sys3");
    model.nodeGroup("grp2").add("coordsys", "sys4");
    model.nodeGroup("grp2").label("\u5750\u6807\u7cfb");

    model.component("comp1").physics().create("solid", "SolidMechanics", "geom1");

    model.study("std1").feature("stat").setSolveFor("/physics/solid", false);

    model.component("comp1").physics().create("c", "CoefficientFormPDE", "geom1");

    model.study("std1").feature("stat").setSolveFor("/physics/c", false);

    model.component("comp1").physics("c").prop("EquationForm").set("form", "Automatic");
    model.component("comp1").physics().create("c2", "CoefficientFormPDE", "geom1");

    model.study("std1").feature("stat").setSolveFor("/physics/c2", false);

    model.component("comp1").physics("c2").prop("EquationForm").set("form", "Automatic");
    model.component("comp1").physics().create("c3", "CoefficientFormPDE", "geom1");

    model.study("std1").feature("stat").setSolveFor("/physics/c3", false);

    model.component("comp1").physics("c3").prop("EquationForm").set("form", "Automatic");
    model.component("comp1").physics("solid").prop("StructuralTransientBehavior")
         .set("StructuralTransientBehavior", "Quasistatic");
    model.component("comp1").physics("solid").prop("ShapeProperty").set("order_displacement", 1);
    model.component("comp1").physics("solid").create("hmm1", "HyperelasticModel", 3);
    model.component("comp1").physics("solid").feature("hmm1").selection().all();
    model.component("comp1").physics("solid").feature("hmm1").create("fib1", "Fiber", 3);

    model.component("comp1").material().create("mat1", "Common");
    model.component("comp1").material("mat1").propertyGroup().create("Lame", "Lame", "Lame_parameters");
    model.component("comp1").material("mat1").propertyGroup("Lame").set("lambLame", new String[]{"lambda_lame"});
    model.component("comp1").material("mat1").propertyGroup("Lame").set("muLame", new String[]{"mu_lame"});
    model.component("comp1").material("mat1").propertyGroup("def").set("density", new String[]{"rhos"});
    model.component("comp1").material("mat1").propertyGroup()
         .create("HolzapfelGasserOgden", "HolzapfelGasserOgden", "Holzapfel_Gasser_Ogden");
    model.component("comp1").material("mat1").propertyGroup("HolzapfelGasserOgden").set("k1HGO", new String[]{"af"});
    model.component("comp1").material("mat1").propertyGroup("HolzapfelGasserOgden").set("k2HGO", new String[]{"bf"});
    model.component("comp1").material("mat1").propertyGroup("HolzapfelGasserOgden").set("k3HGO", new String[]{"0"});

    model.component("comp1").physics("c").label("\u7535\u751f\u7406\u5b66\uff1a\u8de8\u819c\u7535\u4f4d (Phi)");
    model.component("comp1").physics("c").prop("Units").set("DependentVariableQuantity", "electricpotential");
    model.component("comp1").physics("c").prop("Units").setIndex("CustomSourceTermUnit", "A/m^3", 0, 0);
    model.component("comp1").physics("c").prop("ShapeProperty").set("order", 1);
    model.component("comp1").physics("c").prop("ShapeProperty").set("frame", "material");
    model.component("comp1").physics("c").field("dimensionless").field("Phi");
    model.component("comp1").physics("c").field("dimensionless").component(1, "Phi");
    model.component("comp1").physics("c2")
         .label("\u7535\u751f\u7406\u5b66\uff1a\u6162\u8fc7\u7a0b\u7684\u7535\u5bfc (r)");
    model.component("comp1").physics("c2").prop("Units").setIndex("CustomSourceTermUnit", "1/s", 0, 0);
    model.component("comp1").physics("c2").prop("ShapeProperty").set("order", 1);
    model.component("comp1").physics("c2").prop("ShapeProperty").set("frame", "material");
    model.component("comp1").physics("c2").field("dimensionless").field("r");
    model.component("comp1").physics("c2").field("dimensionless").component(1, "r");
    model.component("comp1").physics("c3").label("\u4e3b\u5e94\u529b (Sa)");
    model.component("comp1").physics("c3").prop("Units").set("DependentVariableQuantity", "stress");
    model.component("comp1").physics("c3").prop("Units").setIndex("CustomSourceTermUnit", "N/m^2/s", 0, 0);
    model.component("comp1").physics("c3").prop("ShapeProperty").set("order", 1);
    model.component("comp1").physics("c3").prop("ShapeProperty").set("frame", "material");
    model.component("comp1").physics("c3").field("dimensionless").field("Sa");
    model.component("comp1").physics("c3").field("dimensionless").component(1, "Sa");

    model.component("comp1").variable().create("var2");
    model.component("comp1").variable("var2").label("\u7535\u751f\u7406\u53d8\u91cf");

//    To import content from file, use:
//    model.component("comp1").variable("var2").loadFile("FILENAME");
    model.component("comp1").variable("var2").set("phi", "(Phi-deltaPhi)/betaPhi", "\u65e0\u91cf\u7eb2\u7535\u4f4d");
    model.component("comp1").variable("var2")
         .set("eps_delay", "e0+(einf-e0)*exp( -exp( -zeta*(Phi-Phibar) ) )", "\u5ef6\u8fdf\u51fd\u6570");
    model.component("comp1").variable("var2").set("tact", "50[ms]-Z/cL*50[ms]", "\u6fc0\u6d3b\u65f6\u95f4");
    model.component("comp1").variable("var2")
         .set("betat", "ts*(1-tau0*(tact-t0)/(t1-t0))", "\u65f6\u95f4\u6bd4\u4f8b\u56e0\u5b50");
    model.component("comp1").variable("var2")
         .set("lambda", "sqrt(solid.hmm1.fib1.IaCIe)", "\u7ea4\u7ef4\u65b9\u5411\u7684\u62c9\u4f38");
    model.component("comp1").variable("var2").set("thetaSwitch", "if(lambda>1,1,0)", "\u6d3b\u5316\u53c2\u6570");
    model.component("comp1").variable("var2")
         .set("Ie", "Cm*betaPhi/betat*(c*phi*(phi-alpha)*(phi-1)+r*phi)", "\u79bb\u5b50\u6d41\uff08\u7eaf\u7535\uff09");
    model.component("comp1").variable("var2")
         .set("Im", "Cm*betaPhi/betat*thetaSwitch*Gs*(lambda-1)*(phi-phis)", "\u79bb\u5b50\u6d41\uff08\u62c9\u4f38\u5f15\u8d77\u7684\uff09");
    model.component("comp1").variable("var2")
         .set("D_iso", "d_iso*(Cm*Chi_m)", "\u5404\u5411\u540c\u6027\u7535\u5bfc\u7387");
    model.component("comp1").variable("var2")
         .set("D_ani", "d_ani*(Cm*Chi_m)", "\u5404\u5411\u5f02\u6027\u7535\u5bfc\u7387\uff0c\u7ea4\u7ef4\u65b9\u5411");
    model.component("comp1").variable("var2")
         .set("DfibXX", "solid.hmm1.fib1.a0X*D_ani*solid.hmm1.fib1.a0X", "\u7535\u5bfc\u7387\u5f20\u91cf\uff0c\u5168\u5c40\u5750\u6807\u7cfb");
    model.component("comp1").variable("var2")
         .set("DfibXY", "solid.hmm1.fib1.a0X*D_ani*solid.hmm1.fib1.a0Y", "\u7535\u5bfc\u7387\u5f20\u91cf\uff0c\u5168\u5c40\u5750\u6807\u7cfb");
    model.component("comp1").variable("var2")
         .set("DfibXZ", "solid.hmm1.fib1.a0X*D_ani*solid.hmm1.fib1.a0Z", "\u7535\u5bfc\u7387\u5f20\u91cf\uff0c\u5168\u5c40\u5750\u6807\u7cfb");
    model.component("comp1").variable("var2")
         .set("DfibYY", "solid.hmm1.fib1.a0Y*D_ani*solid.hmm1.fib1.a0Y", "\u7535\u5bfc\u7387\u5f20\u91cf\uff0c\u5168\u5c40\u5750\u6807\u7cfb");
    model.component("comp1").variable("var2")
         .set("DfibYZ", "solid.hmm1.fib1.a0Y*D_ani*solid.hmm1.fib1.a0Z", "\u7535\u5bfc\u7387\u5f20\u91cf\uff0c\u5168\u5c40\u5750\u6807\u7cfb");
    model.component("comp1").variable("var2")
         .set("DfibZZ", "solid.hmm1.fib1.a0Z*D_ani*solid.hmm1.fib1.a0Z", "\u7535\u5bfc\u7387\u5f20\u91cf\uff0c\u5168\u5c40\u5750\u6807\u7cfb");

    model.component("comp1").physics("c").feature("cfeq1").setIndex("c", "D_iso+DfibXX", 0, 0);
    model.component("comp1").physics("c").feature("cfeq1").setIndex("c", "D_iso+DfibXX", 0, 0);
    model.component("comp1").physics("c").feature("cfeq1").setIndex("c", "DfibXY", 0, 1);
    model.component("comp1").physics("c").feature("cfeq1").setIndex("c", "DfibXY", 0, 3);
    model.component("comp1").physics("c").feature("cfeq1").setIndex("c", "DfibXZ", 0, 2);
    model.component("comp1").physics("c").feature("cfeq1").setIndex("c", "DfibXZ", 0, 6);
    model.component("comp1").physics("c").feature("cfeq1").setIndex("c", "D_iso+DfibYY", 0, 4);
    model.component("comp1").physics("c").feature("cfeq1").setIndex("c", "D_iso+DfibYY", 0, 4);
    model.component("comp1").physics("c").feature("cfeq1").setIndex("c", "DfibYZ", 0, 5);
    model.component("comp1").physics("c").feature("cfeq1").setIndex("c", "DfibYZ", 0, 7);
    model.component("comp1").physics("c").feature("cfeq1").setIndex("c", "D_iso+DfibZZ", 0, 8);
    model.component("comp1").physics("c").feature("cfeq1").setIndex("c", "D_iso+DfibZZ", 0, 8);
    model.component("comp1").physics("c").feature("cfeq1").setIndex("f", "-Chi_m*(Ie+Im)", 0);
    model.component("comp1").physics("c").feature("cfeq1").setIndex("da", "Chi_m*Cm", 0);
    model.component("comp1").physics("c").feature("init1")
         .set("Phi", "Phir+70[mV]*(Z>-1e-4[mm])*(X<31[mm])*(X>0[mm])*(Y>-10[mm])*(Y<10[mm])");
    model.component("comp1").physics("c2").feature("cfeq1").setIndex("c", new int[]{0, 0, 0, 0, 0, 0, 0, 0, 0}, 0);
    model.component("comp1").physics("c2").feature("cfeq1")
         .setIndex("a", "(1/betat)*(gamma+(mu1/(phi+mu2))*c*phi*(phi-b-1))", 0);
    model.component("comp1").physics("c2").feature("cfeq1")
         .setIndex("f", "(1/betat)*(-gamma*c*phi*(phi-b-1)-mu1/(phi+mu2)*r^2)", 0);
    model.component("comp1").physics("c3").feature("cfeq1").setIndex("c", new int[]{0, 0, 0, 0, 0, 0, 0, 0, 0}, 0);
    model.component("comp1").physics("c3").feature("cfeq1").setIndex("a", "eps_delay", 0);
    model.component("comp1").physics("c3").feature("cfeq1").setIndex("f", "eps_delay*kT*(Phi-Phir)", 0);
    model.component("comp1").physics("solid").feature("hmm1").feature("fib1").set("coordinateSystem", "sys3");
    model.component("comp1").physics("solid").feature("hmm1").feature("fib1").set("directionSelection", "x3");
    model.component("comp1").physics("solid").feature("hmm1").create("exs1", "ExternalStress", 3);
    model.component("comp1").physics("solid").feature("hmm1").feature("exs1").set("coordinateSystem", "sys3");
    model.component("comp1").physics("solid").feature("hmm1").feature("exs1")
         .set("Sext", new String[]{"vs*Sa", "0", "0", "0", "vn*Sa", "0", "0", "0", "vf*Sa"});
    model.component("comp1").physics("solid").create("disp1", "Displacement2", 2);
    model.component("comp1").physics("solid").feature("disp1").selection().named("sel1");
    model.component("comp1").physics("solid").feature("disp1").setIndex("Direction", "prescribed", 0);
    model.component("comp1").physics("solid").feature("disp1").setIndex("Direction", "prescribed", 1);
    model.component("comp1").physics("solid").feature("disp1").setIndex("Direction", "prescribed", 2);
    model.component("comp1").physics("solid").create("enc1", "EnclosedCavity", 2);
    model.component("comp1").physics("solid").feature("enc1").feature().remove("fl1");
    model.component("comp1").physics("solid").feature("enc1").label("\u5c01\u95ed\u8154\uff1a\u5de6\u5fc3\u5ba4");
    model.component("comp1").physics("solid").feature("enc1").selection().named("sel2");
    model.component("comp1").physics("solid").feature("enc1").set("volumeType", "openSurface");
    model.component("comp1").physics("solid").feature("enc1").selection("referencePoint").set(2);
    model.component("comp1").physics("solid").feature().duplicate("enc2", "enc1");
    model.component("comp1").physics("solid").feature("enc2").label("\u5c01\u95ed\u8154\uff1a\u53f3\u5fc3\u5ba4");
    model.component("comp1").physics("solid").feature("enc2").selection().set(19, 20, 21, 22);

    model.nodeGroup().create("grp3", "Physics", "solid");
    model.nodeGroup("grp3").placeAfter("disp1");
    model.nodeGroup("grp3").add("enc1");
    model.nodeGroup("grp3").add("enc2");
    model.nodeGroup("grp3").label("\u5185\u90e8\u5bb9\u79ef");

    model.study().create("std2");
    model.study("std2").create("time", "Transient");
    model.study("std2").feature("time").setSolveFor("/physics/wd", false);
    model.study("std2").feature("time").setSolveFor("/physics/wd2", false);
    model.study("std2").feature("time").setSolveFor("/physics/cc", false);
    model.study("std2").feature("time").setSolveFor("/physics/solid", true);
    model.study("std2").feature("time").setSolveFor("/physics/c", true);
    model.study("std2").feature("time").setSolveFor("/physics/c2", true);
    model.study("std2").feature("time").setSolveFor("/physics/c3", true);
    model.study("std2").label("\u7814\u7a76\uff1a\u6fc0\u52b1-\u6536\u7f29");
    model.study("std2").feature("time").set("tunit", "ms");
    model.study("std2").feature("time").set("tlist", "range(0,5,320)");
    model.study("std2").feature("time").set("usesol", true);
    model.study("std2").feature("time").set("notsolmethod", "sol");
    model.study("std2").feature("time").set("notstudy", "std1");
    model.study("std2").createAutoSequences("sol");
    model.study("std2").createAutoSequences("jobs");

    model.sol("sol2").runFromTo("st1", "v1");

    model.result().create("pg5", "PlotGroup3D");
    model.result("pg5").set("data", "dset2");
    model.result("pg5").label("\u5e94\u529b (solid)");
    model.result("pg5").set("frametype", "spatial");
    model.result("pg5").create("vol1", "Volume");
    model.result("pg5").feature("vol1").set("expr", new String[]{"solid.misesGp"});
    model.result("pg5").feature("vol1").set("threshold", "manual");
    model.result("pg5").feature("vol1").set("thresholdvalue", 0.2);
    model.result("pg5").feature("vol1").set("colortable", "Rainbow");
    model.result("pg5").feature("vol1").set("colortabletrans", "none");
    model.result("pg5").feature("vol1").set("colorscalemode", "linear");
    model.result("pg5").feature("vol1").set("resolution", "custom");
    model.result("pg5").feature("vol1").set("refine", 2);
    model.result("pg5").feature("vol1").set("colortable", "Prism");
    model.result().create("pg6", "PlotGroup3D");
    model.result("pg6").set("data", "dset2");
    model.result("pg6").create("slc1", "Slice");
    model.result("pg6").label("\u7535\u751f\u7406\u5b66\uff1a\u8de8\u819c\u7535\u4f4d (Phi)");
    model.result("pg6").feature("slc1").set("expr", "Phi");
    model.result().create("pg7", "PlotGroup3D");
    model.result("pg7").set("data", "dset2");
    model.result("pg7").create("slc1", "Slice");
    model.result("pg7").label("\u7535\u751f\u7406\u5b66\uff1a\u6162\u8fc7\u7a0b\u7684\u7535\u5bfc (r)");
    model.result("pg7").feature("slc1").set("expr", "r");
    model.result().create("pg8", "PlotGroup3D");
    model.result("pg8").set("data", "dset2");
    model.result("pg8").create("slc1", "Slice");
    model.result("pg8").label("\u4e3b\u5e94\u529b (Sa)");
    model.result("pg8").feature("slc1").set("expr", "Sa");
    model.result("pg5").run();
    model.result().dataset("dset2").set("frametype", "material");
    model.result("pg5").run();
    model.result().remove("pg5");
    model.result().remove("pg6");
    model.result().remove("pg7");
    model.result().remove("pg8");
    model.result().create("pg5", "PlotGroup3D");
    model.result("pg5").set("data", "dset2");
    model.result("pg5").label("\u7ea4\u7ef4, \u8d85\u5f39\u6027\u6750\u6599 1 (solid)");
    model.result("pg5").set("showlegends", true);
    model.result("pg5").set("titletype", "manual");
    model.result("pg5")
         .set("title", "\u7ea4\u7ef4\u5e94\u529b, \u7ea4\u7ef4, \u8d85\u5f39\u6027\u6750\u6599 1 (solid)");
    model.result("pg5").set("showlegendsunit", true);
    model.result("pg5").create("str1", "Streamline");
    model.result("pg5").feature("str1")
         .set("expr", new String[]{"solid.hmm1.fib1.a0X", "solid.hmm1.fib1.a0Y", "solid.hmm1.fib1.a0Z"});
    model.result("pg5").feature("str1").set("linetype", "tube");
    model.result("pg5").feature("str1").set("posmethod", "uniform");
    model.result("pg5").feature("str1").set("revcoordsys", "cylindrical");
    model.result("pg5").feature("str1").label("\u7ea4\u7ef4 1");
    model.result("pg5").feature("str1").set("inheritplot", "none");
    model.result("pg5").feature("str1").create("col", "Color");
    model.result("pg5").feature("str1").feature("col").set("colortabletrans", "none");
    model.result("pg5").feature("str1").feature("col").set("colorscalemode", "linear");
    model.result("pg5").feature("str1").feature("col").set("expr", "solid.hmm1.fib1.sa");
    model.result("pg5").feature("str1").feature("col").set("colortable", "Rainbow");
    model.result("pg5").label("\u7ea4\u7ef4, \u8d85\u5f39\u6027\u6750\u6599 1 (solid)");
    model.result("pg5").run();
    model.result("pg5").label("\u7ea4\u7ef4");
    model.result("pg5").set("title", "\u7ea4\u7ef4\u65b9\u5411");
    model.result("pg5").run();
    model.result("pg5").run();
    model.result("pg5").feature("str1").label("\u5fc3\u5185\u819c");
    model.result("pg5").feature("str1").set("posmethod", "selection");
    model.result("pg5").feature("str1").set("selpointdistr", "meshbased");
    model.result("pg5").feature("str1").set("selmeshrefine", 2);
    model.result("pg5").feature("str1").selection().set(1, 2, 3, 8, 9, 13, 14, 17);
    model.result("pg5").feature("str1").set("linetype", "line");
    model.result("pg5").feature("str1").create("filt1", "Filter");
    model.result("pg5").run();
    model.result("pg5").run();
    model.result("pg5").feature("str1").feature("col").set("expr", "theta");
    model.result("pg5").feature("str1").feature("col").set("unit", "deg");
    model.result("pg5").feature("str1").feature("col").set("rangecoloractive", true);
    model.result("pg5").feature("str1").feature("col").set("rangecolormin", -60);
    model.result("pg5").feature("str1").feature("col").set("rangecolormax", 60);
    model.result("pg5").run();
    model.result("pg5").feature("str1").feature("filt1").set("expr", "Beta>0.95");
    model.result("pg5").run();
    model.result("pg5").feature().duplicate("str2", "str1");
    model.result("pg5").run();
    model.result("pg5").feature("str2").label("\u5fc3\u808c");
    model.result("pg5").feature("str2").selection().set(2, 8, 13, 14);

    return model;
  }

  public static Model run2(Model model) {
    model.result("pg5").feature("str2").set("selmeshrefine", 1);
    model.result("pg5").feature("str2").set("inheritplot", "str1");
    model.result("pg5").run();
    model.result("pg5").feature("str2").feature("filt1").set("expr", "(Beta>0.45)*(Beta<0.55)*(Z<-cL/9)");
    model.result("pg5").run();
    model.result("pg5").feature().duplicate("str3", "str2");
    model.result("pg5").run();
    model.result("pg5").feature("str3").label("\u5fc3\u5916\u819c");
    model.result("pg5").feature("str3").selection().set(2, 3, 5, 8, 9, 10, 11, 13, 14, 15, 17, 18);
    model.result("pg5").feature("str3").set("selmeshrefine", 3);
    model.result("pg5").run();
    model.result("pg5").feature("str3").feature("filt1")
         .set("expr", "Beta<0.05*((sys4.phi>-100[deg])*(sys4.phi<190[deg])*(Z>-cL/3) || (Z<-cL/3))");
    model.result("pg5").run();

    model.component("comp1").view("view1").set("showgrid", false);

    model.result("pg5").run();
    model.result("pg5").set("showlegends", false);

    model.component("comp1").view("view1").set("showaxisorientation", false);

    model.result("pg5").set("titletype", "none");
    model.result("pg5").set("showlegends", true);

    model.component("comp1").view("view1").set("showaxisorientation", true);

    model.result("pg5").set("titletype", "label");
    model.result().create("pg6", "PlotGroup3D");
    model.result("pg6").run();
    model.result().configuration().create("prfu1", "PreferredUnits");
    model.result().configuration("prfu1")
         .setIndex("quantityunits", new String[]{"electricpotential", "\u7535\u52bf", "V", "V"}, 0);
    model.result().configuration("prfu1").setIndex("quantityunits", "mV", 0, 3);
    model.result().configuration("prfu1").apply();
    model.result("pg6").run();
    model.result("pg6").label("\u7535\u4f4d");
    model.result("pg6").set("data", "dset2");
    model.result("pg6").create("vol1", "Volume");
    model.result("pg6").feature("vol1").set("expr", "Phi");
    model.result("pg6").feature("vol1").set("rangecoloractive", true);
    model.result("pg6").feature("vol1").set("rangecolormin", -80);
    model.result("pg6").feature("vol1").set("rangecolormax", 20);
    model.result("pg6").feature("vol1").create("def1", "Deform");
    model.result("pg6").run();
    model.result("pg6").feature("vol1").feature("def1").set("scaleactive", true);
    model.result("pg6").feature("vol1").feature("def1").set("scale", 1);

    model.study("std2").setGenPlots(false);
    model.study("std2").feature("time").set("plot", true);
    model.study("std2").feature("time").set("plotgroup", "pg6");
    model.study("std2").createAutoSequences("all");

    model.sol("sol2").runAll();

    model.result("pg5").run();
    model.result().create("pg7", "PlotGroup3D");
    model.result("pg7").run();
    model.result("pg7").label("\u6536\u7f29\uff0c\u5feb\u7167");
    model.result("pg7").set("data", "dset2");
    model.result("pg7").set("titletype", "manual");
    model.result("pg7").set("title", "\u7535\u4f4d\u548c\u53d8\u5f62");
    model.result("pg7").set("plotarrayenable", true);
    model.result("pg7").set("arrayshape", "square");
    model.result("pg7").set("arrayplane", "xz");
    model.result("pg7").create("slc1", "Slice");
    model.result("pg7").feature("slc1").set("arraydim", "2");
    model.result("pg7").feature("slc1").set("data", "dset2");
    model.result("pg7").feature("slc1").setIndex("looplevel", 2, 0);
    model.result("pg7").feature("slc1").set("expr", "Phi");
    model.result("pg7").feature("slc1").set("quickplane", "zx");
    model.result("pg7").feature("slc1").set("quickynumber", 1);
    model.result("pg7").feature("slc1").set("manualindexing", true);
    model.result("pg7").feature("slc1").create("def1", "Deform");
    model.result("pg7").run();
    model.result("pg7").feature("slc1").feature("def1").set("scaleactive", true);
    model.result("pg7").feature("slc1").feature("def1").set("scale", 1);
    model.result("pg7").run();
    model.result("pg7").feature("slc1").set("arraydim", "2");
    model.result("pg7").run();
    model.result("pg7").feature().duplicate("slc2", "slc1");
    model.result("pg7").feature("slc2").set("arraydim", "2");
    model.result("pg7").run();
    model.result("pg7").feature("slc2").set("titletype", "none");
    model.result("pg7").feature("slc2").set("quickplane", "xy");
    model.result("pg7").feature("slc2").set("quickznumber", 1);
    model.result("pg7").feature("slc2").set("colorlegend", false);
    model.result("pg7").feature("slc2").set("inheritplot", "slc1");
    model.result("pg7").run();
    model.result("pg7").create("ann1", "Annotation");
    model.result("pg7").feature("ann1").set("arraydim", "2");
    model.result("pg7").feature("ann1").set("text", "t=5 ms");
    model.result("pg7").feature("ann1").set("poszexpr", 20);
    model.result("pg7").feature("ann1").set("manualindexing", true);
    model.result("pg7").feature("slc1").set("arraydim", "2");
    model.result("pg7").run();
    model.result("pg7").feature().duplicate("slc3", "slc1");
    model.result("pg7").feature().duplicate("slc4", "slc2");
    model.result("pg7").feature().duplicate("ann2", "ann1");
    model.result("pg7").feature("slc3").set("arraydim", "2");
    model.result("pg7").run();
    model.result("pg7").feature("slc3").set("titletype", "none");
    model.result("pg7").feature("slc3").set("colorlegend", false);
    model.result("pg7").feature("slc3").set("inheritplot", "slc1");
    model.result("pg7").feature("slc3").setIndex("looplevel", 8, 0);
    model.result("pg7").feature("slc3").set("colindex", 1);
    model.result("pg7").feature("slc4").set("arraydim", "2");
    model.result("pg7").run();
    model.result("pg7").feature("slc4").setIndex("looplevel", 8, 0);
    model.result("pg7").feature("slc4").set("colindex", 1);
    model.result("pg7").feature("ann2").set("arraydim", "2");
    model.result("pg7").run();
    model.result("pg7").feature("ann2").set("text", "t=35 ms");
    model.result("pg7").feature("ann2").set("colindex", 1);
    model.result("pg7").run();
    model.result("pg7").feature("slc3").set("arraydim", "2");
    model.result("pg7").run();
    model.result("pg7").feature().duplicate("slc5", "slc3");
    model.result("pg7").feature().duplicate("slc6", "slc4");
    model.result("pg7").feature().duplicate("ann3", "ann2");
    model.result("pg7").feature("slc5").set("arraydim", "2");
    model.result("pg7").run();
    model.result("pg7").feature("slc5").setIndex("looplevel", 12, 0);
    model.result("pg7").feature("slc5").set("colindex", 2);
    model.result("pg7").feature("slc6").set("arraydim", "2");
    model.result("pg7").run();
    model.result("pg7").feature("slc6").setIndex("looplevel", 12, 0);
    model.result("pg7").feature("slc6").set("colindex", 2);
    model.result("pg7").feature("ann3").set("arraydim", "2");
    model.result("pg7").run();
    model.result("pg7").feature("ann3").set("text", "t=50 ms");
    model.result("pg7").feature("ann3").set("colindex", 2);
    model.result("pg7").run();
    model.result("pg7").feature("slc5").set("arraydim", "2");
    model.result("pg7").run();
    model.result("pg7").feature().duplicate("slc7", "slc5");
    model.result("pg7").feature().duplicate("slc8", "slc6");
    model.result("pg7").feature().duplicate("ann4", "ann3");
    model.result("pg7").feature("slc7").set("arraydim", "2");
    model.result("pg7").run();
    model.result("pg7").feature("slc7").setIndex("looplevel", 21, 0);
    model.result("pg7").feature("slc7").set("colindex", 3);
    model.result("pg7").feature("slc8").set("arraydim", "2");
    model.result("pg7").run();
    model.result("pg7").feature("slc8").setIndex("looplevel", 21, 0);
    model.result("pg7").feature("slc8").set("colindex", 3);
    model.result("pg7").feature("ann4").set("arraydim", "2");
    model.result("pg7").run();
    model.result("pg7").feature("ann4").set("text", "t=100 ms");
    model.result("pg7").feature("ann4").set("colindex", 3);
    model.result("pg7").run();
    model.result("pg7").feature("slc7").set("arraydim", "2");
    model.result("pg7").run();
    model.result("pg7").feature().duplicate("slc9", "slc7");
    model.result("pg7").feature().duplicate("slc10", "slc8");
    model.result("pg7").feature().duplicate("ann5", "ann4");
    model.result("pg7").feature("slc9").set("arraydim", "2");
    model.result("pg7").run();
    model.result("pg7").feature("slc9").setIndex("looplevel", 30, 0);
    model.result("pg7").feature("slc9").set("rowindex", -1);
    model.result("pg7").feature("slc9").set("colindex", 0);
    model.result("pg7").feature("slc10").set("arraydim", "2");
    model.result("pg7").run();
    model.result("pg7").feature("slc10").setIndex("looplevel", 30, 0);
    model.result("pg7").feature("slc10").set("rowindex", -1);
    model.result("pg7").feature("slc10").set("colindex", 0);
    model.result("pg7").feature("ann5").set("arraydim", "2");
    model.result("pg7").run();
    model.result("pg7").feature("ann5").set("text", "t=145 ms");
    model.result("pg7").feature("ann5").set("poszexpr", -80);
    model.result("pg7").feature("ann5").set("rowindex", -1);
    model.result("pg7").feature("ann5").set("colindex", 0);
    model.result("pg7").run();
    model.result("pg7").feature("slc9").set("arraydim", "2");
    model.result("pg7").run();
    model.result("pg7").feature().duplicate("slc11", "slc9");
    model.result("pg7").feature().duplicate("slc12", "slc10");
    model.result("pg7").feature().duplicate("ann6", "ann5");
    model.result("pg7").feature("slc11").set("arraydim", "2");
    model.result("pg7").run();
    model.result("pg7").feature("slc11").setIndex("looplevel", 41, 0);
    model.result("pg7").feature("slc11").set("colindex", 1);
    model.result("pg7").feature("slc12").set("arraydim", "2");
    model.result("pg7").run();
    model.result("pg7").feature("slc12").setIndex("looplevel", 41, 0);
    model.result("pg7").feature("slc12").set("colindex", 1);
    model.result("pg7").feature("ann6").set("arraydim", "2");
    model.result("pg7").run();
    model.result("pg7").feature("ann6").set("text", "t=200 ms");
    model.result("pg7").feature("ann6").set("colindex", 1);
    model.result("pg7").run();
    model.result("pg7").feature("slc11").set("arraydim", "2");
    model.result("pg7").run();
    model.result("pg7").feature().duplicate("slc13", "slc11");
    model.result("pg7").feature().duplicate("slc14", "slc12");
    model.result("pg7").feature().duplicate("ann7", "ann6");
    model.result("pg7").feature("slc13").set("arraydim", "2");
    model.result("pg7").run();
    model.result("pg7").feature("slc13").setIndex("looplevel", 51, 0);
    model.result("pg7").feature("slc13").set("colindex", 2);
    model.result("pg7").feature("slc14").set("arraydim", "2");
    model.result("pg7").run();
    model.result("pg7").feature("slc14").setIndex("looplevel", 51, 0);
    model.result("pg7").feature("slc14").set("colindex", 2);
    model.result("pg7").feature("ann7").set("arraydim", "2");
    model.result("pg7").run();
    model.result("pg7").feature("ann7").set("colindex", 2);
    model.result("pg7").feature("ann7").set("text", "t=250 ms");
    model.result("pg7").run();
    model.result("pg7").feature("slc13").set("arraydim", "2");
    model.result("pg7").run();
    model.result("pg7").feature().duplicate("slc15", "slc13");
    model.result("pg7").feature().duplicate("slc16", "slc14");
    model.result("pg7").feature().duplicate("ann8", "ann7");
    model.result("pg7").feature("slc15").set("arraydim", "2");
    model.result("pg7").run();
    model.result("pg7").feature("slc15").setIndex("looplevel", 61, 0);
    model.result("pg7").feature("slc15").set("colindex", 3);
    model.result("pg7").feature("slc16").set("arraydim", "2");
    model.result("pg7").run();
    model.result("pg7").feature("slc16").setIndex("looplevel", 61, 0);
    model.result("pg7").feature("slc16").set("colindex", 3);
    model.result("pg7").feature("ann8").set("arraydim", "2");
    model.result("pg7").run();
    model.result("pg7").feature("ann8").set("text", "t=300 ms");
    model.result("pg7").feature("ann8").set("colindex", 3);
    model.result("pg7").feature("slc2").set("arraydim", "2");
    model.result("pg7").run();
    model.result("pg7").run();

    model.component("comp1").view("view1").set("transparency", false);

    model.result("pg7").run();
    model.result().create("pg8", "PlotGroup1D");
    model.result("pg8").run();
    model.result("pg8").label("\u5bb9\u79ef\u53d8\u5316");
    model.result("pg8").set("data", "dset2");
    model.result("pg8").set("ylabelactive", true);
    model.result("pg8").set("titletype", "none");
    model.result("pg8").set("ylabel", "\u5f53\u524d\u5bb9\u79ef (ml)");
    model.result("pg8").set("legendpos", "uppermiddle");
    model.result("pg8").create("glob1", "Global");
    model.result("pg8").feature("glob1").set("markerpos", "datapoints");
    model.result("pg8").feature("glob1").set("linewidth", "preference");
    model.result("pg8").feature("glob1").setIndex("expr", "solid.enc1.V", 0);
    model.result("pg8").feature("glob1").setIndex("unit", "ml", 0);
    model.result("pg8").feature("glob1").setIndex("expr", "solid.enc2.V", 1);
    model.result("pg8").feature("glob1").setIndex("unit", "ml", 1);
    model.result("pg8").feature("glob1").set("legendmethod", "manual");
    model.result("pg8").feature("glob1").setIndex("legends", "\u5de6\u5fc3\u5ba4", 0);
    model.result("pg8").feature("glob1").setIndex("legends", "\u53f3\u5fc3\u5ba4", 1);
    model.result("pg8").run();
    model.result().dataset().create("cpt1", "CutPoint3D");
    model.result().dataset("cpt1").label("Z=-1mm \u5904\u7684\u70b9");
    model.result().dataset("cpt1").set("data", "dset2");
    model.result().dataset("cpt1").set("pointx", 25);
    model.result().dataset("cpt1").set("pointy", 0);
    model.result().dataset("cpt1").set("pointz", -1);
    model.result().dataset().duplicate("cpt2", "cpt1");
    model.result().dataset("cpt2").label("Z=-40mm \u5904\u7684\u70b9");
    model.result().dataset("cpt2").set("pointx", 20);
    model.result().dataset("cpt2").set("pointz", -40);
    model.result().dataset().duplicate("cpt3", "cpt2");
    model.result().dataset("cpt3").label("Z=-60mm \u5904\u7684\u70b9");
    model.result().dataset("cpt3").set("pointx", 0);
    model.result().dataset("cpt3").set("pointz", -60);
    model.result().create("pg9", "PlotGroup1D");
    model.result("pg9").run();
    model.result("pg9").label("\u6fc0\u52b1\u65f6\u95f4");
    model.result("pg9").set("titletype", "none");
    model.result("pg9").create("ptgr1", "PointGraph");
    model.result("pg9").feature("ptgr1").set("markerpos", "datapoints");
    model.result("pg9").feature("ptgr1").set("linewidth", "preference");
    model.result("pg9").feature("ptgr1").set("data", "cpt1");
    model.result("pg9").feature("ptgr1").set("expr", "Phi");
    model.result("pg9").feature("ptgr1").set("legend", true);
    model.result("pg9").feature("ptgr1").set("legendmethod", "manual");
    model.result("pg9").feature("ptgr1").setIndex("legends", "Z=-1 mm \u5904\u7684\u70b9", 0);
    model.result("pg9").feature().duplicate("ptgr2", "ptgr1");
    model.result("pg9").run();
    model.result("pg9").feature("ptgr2").set("data", "cpt2");
    model.result("pg9").feature("ptgr2").set("titletype", "none");
    model.result("pg9").feature("ptgr2").setIndex("legends", "Z=-40 mm \u5904\u7684\u70b9", 0);
    model.result("pg9").feature().duplicate("ptgr3", "ptgr2");
    model.result("pg9").run();
    model.result("pg9").feature("ptgr3").set("data", "cpt3");
    model.result("pg9").feature("ptgr3").setIndex("legends", "Z=-60 mm \u5904\u7684\u70b9", 0);
    model.result("pg9").run();
    model.result().export().create("anim1", "Animation");
    model.result().export("anim1").set("target", "player");
    model.result().export("anim1").set("fontsize", "9");
    model.result().export("anim1").set("colortheme", "globaltheme");
    model.result().export("anim1").set("customcolor", new double[]{1, 1, 1});
    model.result().export("anim1").set("background", "color");
    model.result().export("anim1").set("gltfincludelines", "on");
    model.result().export("anim1").set("title1d", "on");
    model.result().export("anim1").set("legend1d", "on");
    model.result().export("anim1").set("logo1d", "on");
    model.result().export("anim1").set("options1d", "on");
    model.result().export("anim1").set("title2d", "on");
    model.result().export("anim1").set("legend2d", "on");
    model.result().export("anim1").set("logo2d", "on");
    model.result().export("anim1").set("options2d", "off");
    model.result().export("anim1").set("title3d", "on");
    model.result().export("anim1").set("legend3d", "on");
    model.result().export("anim1").set("logo3d", "on");
    model.result().export("anim1").set("options3d", "off");
    model.result().export("anim1").set("axisorientation", "on");
    model.result().export("anim1").set("grid", "on");
    model.result().export("anim1").set("axes1d", "on");
    model.result().export("anim1").set("axes2d", "on");
    model.result().export("anim1").set("showgrid", "on");
    model.result().export("anim1").label("\u5fc3\u810f\u53d8\u5f62");
    model.result().export("anim1").set("plotgroup", "pg6");
    model.result().export("anim1").set("maxframes", 50);
    model.result("pg6").run();
    model.result("pg6").setIndex("looplevel", 1, 0);
    model.result("pg6").run();
    model.result("pg5").run();

    model.title("\u53cc\u5fc3\u5ba4\u5fc3\u810f\u6a21\u578b");

    model
         .description("\u8fd9\u4e2a\u6807\u51c6\u7684\u53cc\u5fc3\u5ba4\u5fc3\u810f\u6a21\u578b\u7528\u4e8e\u6f14\u793a\u5982\u4f55\u5728\u590d\u6742\u7684\u51e0\u4f55\u4e2d\u8bbe\u7f6e\u7ea4\u7ef4\u65b9\u5411\u3002\u7136\u540e\uff0c\u4f7f\u7528\u8fd9\u4e9b\u7ea4\u7ef4\u901a\u8fc7 Holzapfel-Gasser-Ogden \u5404\u5411\u5f02\u6027\u6750\u6599\u6a21\u578b\u6765\u6a21\u62df\u5fc3\u808c\u7684\u5927\u53d8\u5f62\u3002");

    model.mesh().clearMeshes();

    model.sol("sol1").clearSolutionData();
    model.sol("sol2").clearSolutionData();

    model.label("biventricular_cardiac_model.mph");

    return model;
  }

  public static void main(String[] args) {
    Model model = run();
    run2(model);
  }

}
