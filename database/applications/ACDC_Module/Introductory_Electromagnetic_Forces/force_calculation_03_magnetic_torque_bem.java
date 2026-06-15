/*
 * force_calculation_03_magnetic_torque_bem.java
 */

import com.comsol.model.*;
import com.comsol.model.util.*;

/** Model exported on Apr 29 2026, 17:23 by COMSOL 6.3.0.290. */
public class force_calculation_03_magnetic_torque_bem {

  public static Model run() {
    Model model = ModelUtil.create("Model");

    model
         .modelPath("D:\\Program Files\\COMSOL\\COMSOL63\\Multiphysics\\applications\\ACDC_Module\\Introductory_Electromagnetic_Forces");

    model.component().create("comp1", true);

    model.component("comp1").geom().create("geom1", 3);
    model.component("comp1").geom("geom1").geomRep("comsol");

    model.component("comp1").mesh().create("mesh1");
    model.component("comp1").mesh("mesh1").contribute("geom/detail", true);

    model.component("comp1").physics().create("mfncbe", "MagneticFieldsNoCurrentsBoundaryElements", "geom1");
    model.component("comp1").physics().create("mfnc", "MagnetostaticsNoCurrents", "geom1");
    model.component("comp1").physics().create("mfncbe2", "MagneticFieldsNoCurrentsBoundaryElements", "geom1");

    model.study().create("std1");
    model.study("std1").create("stat", "Stationary");
    model.study("std1").feature("stat").setSolveFor("/physics/mfncbe", true);
    model.study("std1").feature("stat").setSolveFor("/physics/mfnc", true);
    model.study("std1").feature("stat").setSolveFor("/physics/mfncbe2", true);

    model.component("comp1").geom("geom1").insertFile("force_calculation_01_introduction.mph", "geom2");
    model.component("comp1").geom("geom1").run("fin");

    model.component("comp1").view("view1").set("showgrid", false);
    model.component("comp1").view("view1").set("showaxisorientation", false);

    model.component("comp1").geom("geom1").run("fin");

    model.component("comp1").view("view1").set("renderwireframe", true);

//    To import content from file, use:
//    model.param().loadFile("FILENAME");
    model.param()
         .set("hf", "1", "\u78c1\u6781\u5706\u89d2\u7684\u7f51\u683c\u6bd4\u4f8b\u56e0\u5b50\uff08\u7528\u4e8e\u6d4b\u8bd5\u7f51\u683c\u6536\u655b\u6027\uff09");
    model.param().set("Ap", "pi*Rr^2", "\u78c1\u5316\u68d2\uff0c\u78c1\u6781\u8868\u9762\u79ef");
    model.param()
         .set("cf", "2-1/sqrt(2)", "\u6821\u6b63\u56e0\u5b50\uff0c\u7528\u4e8e\u529b\u9a8c\u8bc1\u6a21\u578b");
    model.param()
         .set("qm", "sqrt(4*pi*mu0_const*1[m/H]/cf)*1[Wb]", "\u78c1\u6781\u4e0a\u7684\u78c1\u8377\u91cf\uff0c\u7528\u4e8e\u529b\u9a8c\u8bc1\u6a21\u578b");
    model.param()
         .set("Br", "qm/Ap", "\u5269\u4f59\u78c1\u901a\u5bc6\u5ea6\uff0c\u7528\u4e8e\u529b\u9a8c\u8bc1\u6a21\u578b");
    model.param().set("Brx", "Br*sin(Ra)", "\u5269\u4f59\u78c1\u901a\u5bc6\u5ea6\uff08x \u5206\u91cf\uff09");
    model.param().set("Brz", "Br*cos(Ra)", "\u5269\u4f59\u78c1\u901a\u5bc6\u5ea6\uff08z \u5206\u91cf\uff09");
    model.param()
         .set("mu", "(qm/mu0_const)*1[m]", "\u957f\u5ea6\u4e3a 1[m] \u7684\u78c1\u5316\u68d2\u7684\u78c1\u5076\u6781\u77e9\uff0c\u5176\u4e24\u6781\u4e0a\u7684\u78c1\u8377\u91cf\u7b49\u4e8e qm");
    model.param()
         .set("Bez", "1[N*m]/mu", "\u5047\u8bbe\u5782\u76f4\u78c1\u5316\u68d2\u7684\u78c1\u5076\u6781\u77e9\u7b49\u4e8e mu\uff0c\u8fbe\u5230 1[N*m] \u7684\u626d\u77e9\u6240\u9700\u7684\u5916\u573a");

    model.component("comp1").selection().create("sel1", "Explicit");
    model.component("comp1").selection("sel1").label("\u78c1\u5316\u68d2\u57df");
    model.component("comp1").selection("sel1").set(3, 4);
    model.component("comp1").selection().create("adj1", "Adjacent");
    model.component("comp1").selection("adj1").label("\u78c1\u5316\u68d2\u8868\u9762");
    model.component("comp1").selection("adj1").set("input", new String[]{"sel1"});
    model.component("comp1").selection().create("sel2", "Explicit");
    model.component("comp1").selection("sel2").label("\u78c1\u5316\u68d2\u78c1\u6781\u8868\u9762");
    model.component("comp1").selection("sel2").geom("geom1", 3, 2, new String[]{"exterior"});
    model.component("comp1").selection("sel2").set(3);
    model.component("comp1").selection().create("sel3", "Explicit");
    model.component("comp1").selection("sel3").label("\u78c1\u5316\u68d2\u78c1\u6781\u5706\u89d2");
    model.component("comp1").selection("sel3").geom(2);
    model.component("comp1").selection("sel3").set(17, 19, 22, 24, 42, 43, 44, 45);
    model.component("comp1").selection().create("sel4", "Explicit");
    model.component("comp1").selection("sel4").label("\u529b\u63a2\u6d4b\u57df");
    model.component("comp1").selection("sel4").set(2, 3, 4);
    model.component("comp1").selection().create("adj2", "Adjacent");
    model.component("comp1").selection("adj2").label("\u529b\u63a2\u6d4b\u8868\u9762");
    model.component("comp1").selection("adj2").set("input", new String[]{"sel4"});
    model.component("comp1").selection().create("sel5", "Explicit");
    model.component("comp1").selection("sel5").label("\u5916\u78c1\u573a\u8fdb\u5165");
    model.component("comp1").selection("sel5").geom(2);
    model.component("comp1").selection("sel5").set(3);
    model.component("comp1").selection().create("sel6", "Explicit");
    model.component("comp1").selection("sel6").label("\u5916\u78c1\u573a\u79bb\u5f00");
    model.component("comp1").selection("sel6").geom(2);
    model.component("comp1").selection("sel6").set(4);
    model.component("comp1").selection().create("sel7", "Explicit");
    model.component("comp1").selection("sel7").label("\u5916\u90e8\u7acb\u65b9\u4f53\u8868\u9762");
    model.component("comp1").selection("sel7").geom(2);
    model.component("comp1").selection("sel7").set(1, 2, 3, 4, 5, 54);

    model.component("comp1").material().create("mat1", "Common");
    model.component("comp1").material("mat1").selection().all();
    model.component("comp1").material("mat1").selection().allVoids();
    model.component("comp1").material("mat1").propertyGroup("def").set("relpermeability", new String[]{"1"});

    model.component("comp1").physics("mfncbe").selection().set(-1, 1, 2, 3, 4);
    model.component("comp1").physics("mfncbe").prop("FarField").set("bemFarFieldTol", "1e-6");
    model.component("comp1").physics("mfncbe").create("mfc2", "MagneticFluxConservation", 3);
    model.component("comp1").physics("mfncbe").feature("mfc2").selection().named("sel4");
    model.component("comp1").physics("mfncbe").create("mfc3", "MagneticFluxConservation", 3);
    model.component("comp1").physics("mfncbe").feature("mfc3").selection().named("sel1");
    model.component("comp1").physics("mfncbe").feature("mfc3").set("ConstitutiveRelationBH", "RemanentFluxDensity");
    model.component("comp1").physics("mfncbe").feature("mfc3")
         .set("normBr_crel_BH_RemanentFluxDensity_mat", "userdef");
    model.component("comp1").physics("mfncbe").feature("mfc3")
         .set("normBr_crel_BH_RemanentFluxDensity", "sqrt(Brx^2+Brz^2)");
    model.component("comp1").physics("mfncbe").feature("mfc3")
         .set("e_crel_BH_RemanentFluxDensity", new String[]{"Brx/sqrt(Brx^2+Brz^2)", "0", "Brz/sqrt(Brx^2+Brz^2)"});
    model.component("comp1").physics("mfncbe").create("fcal1", "ForceCalculation", 3);
    model.component("comp1").physics("mfncbe").feature("fcal1").selection().named("sel1");
    model.component("comp1").physics("mfncbe").feature("fcal1").set("ForceName", "BEM_rod");
    model.component("comp1").physics("mfncbe").feature("fcal1").set("TorqueAxis", new int[]{0, 1, 0});
    model.component("comp1").physics("mfncbe").create("fcal2", "ForceCalculation", 3);
    model.component("comp1").physics("mfncbe").feature("fcal2").selection().named("sel4");
    model.component("comp1").physics("mfncbe").feature("fcal2").set("ForceName", "BEM_probe");
    model.component("comp1").physics("mfncbe").feature("fcal2").set("TorqueAxis", new int[]{0, 1, 0});
    model.component("comp1").physics("mfncbe").create("mflx1", "MagneticFluxDensity", 2);
    model.component("comp1").physics("mfncbe").feature("mflx1").selection().named("sel5");
    model.component("comp1").physics("mfncbe").feature("mflx1").set("nB", "Bez");
    model.component("comp1").physics("mfncbe").create("zsp1", "ZeroMagneticScalarPotential", 2);
    model.component("comp1").physics("mfncbe").feature("zsp1").selection().named("sel6");
    model.component("comp1").physics("mfnc").create("mfcs1", "MagneticFluxConservationSolid", 3);
    model.component("comp1").physics("mfnc").feature("mfcs1").selection().named("sel1");
    model.component("comp1").physics("mfnc").feature("mfcs1").set("ConstitutiveRelationBH", "RemanentFluxDensity");
    model.component("comp1").physics("mfnc").feature("mfcs1")
         .set("normBr_crel_BH_RemanentFluxDensity_mat", "userdef");
    model.component("comp1").physics("mfnc").feature("mfcs1")
         .set("normBr_crel_BH_RemanentFluxDensity", "sqrt(Brx^2+Brz^2)");
    model.component("comp1").physics("mfnc").feature("mfcs1")
         .set("e_crel_BH_RemanentFluxDensity", new String[]{"Brx/sqrt(Brx^2+Brz^2)", "0", "Brz/sqrt(Brx^2+Brz^2)"});
    model.component("comp1").physics("mfnc").create("fcal1", "ForceCalculation", 3);
    model.component("comp1").physics("mfnc").feature("fcal1").selection().named("sel1");
    model.component("comp1").physics("mfnc").feature("fcal1").set("ForceName", "FEM_rod");
    model.component("comp1").physics("mfnc").feature("fcal1").set("TorqueAxis", new int[]{0, 1, 0});
    model.component("comp1").physics("mfnc").create("fcal2", "ForceCalculation", 3);
    model.component("comp1").physics("mfnc").feature("fcal2").selection().named("sel4");
    model.component("comp1").physics("mfnc").feature("fcal2").set("ForceName", "FEM_probe");
    model.component("comp1").physics("mfnc").feature("fcal2").set("TorqueAxis", new int[]{0, 1, 0});
    model.component("comp1").physics("mfncbe2").selection().set(-1);
    model.component("comp1").physics("mfncbe2").create("mflx1", "MagneticFluxDensity", 2);
    model.component("comp1").physics("mfncbe2").feature("mflx1").selection().named("sel5");
    model.component("comp1").physics("mfncbe2").feature("mflx1").set("nB", "Bez");
    model.component("comp1").physics("mfncbe2").create("zsp1", "ZeroMagneticScalarPotential", 2);
    model.component("comp1").physics("mfncbe2").feature("zsp1").selection().named("sel6");

    model.component("comp1").multiphysics().create("msspc1", "MagneticScalarScalarPotentialCoupling", 2);
    model.component("comp1").multiphysics("msspc1").set("Vm_dst_physics", "mfncbe2");
    model.component("comp1").multiphysics("msspc1").selection().all();

    model.component("comp1").mesh("mesh1").autoMeshSize(3);
    model.component("comp1").mesh("mesh1").create("ftri1", "FreeTri");
    model.component("comp1").mesh("mesh1").feature("ftri1").selection().named("adj1");
    model.component("comp1").mesh("mesh1").feature("ftri1").create("size1", "Size");
    model.component("comp1").mesh("mesh1").feature("ftri1").feature("size1").selection().named("sel3");
    model.component("comp1").mesh("mesh1").feature("ftri1").feature("size1").set("custom", true);
    model.component("comp1").mesh("mesh1").feature("ftri1").feature("size1").set("hmaxactive", true);
    model.component("comp1").mesh("mesh1").feature("ftri1").feature("size1").set("hmax", "Rrf/hf");
    model.component("comp1").mesh("mesh1").run();
    model.component("comp1").mesh("mesh1").create("ftri2", "FreeTri");
    model.component("comp1").mesh("mesh1").feature("ftri2").selection().named("adj2");
    model.component("comp1").mesh("mesh1").feature("ftri2").create("size1", "Size");
    model.component("comp1").mesh("mesh1").feature("ftri2").feature("size1").set("custom", true);
    model.component("comp1").mesh("mesh1").feature("ftri2").feature("size1").set("hmaxactive", true);
    model.component("comp1").mesh("mesh1").feature("ftri2").feature("size1").set("hmax", "Rr");
    model.component("comp1").mesh("mesh1").run();
    model.component("comp1").mesh("mesh1").create("map1", "Map");
    model.component("comp1").mesh("mesh1").feature("map1").selection().named("sel7");
    model.component("comp1").mesh("mesh1").feature("map1").create("dis1", "Distribution");
    model.component("comp1").mesh("mesh1").feature("map1").feature("dis1").selection().all();
    model.component("comp1").mesh("mesh1").run();

    model.study("std1").setGenPlots(false);
    model.study("std1").create("param", "Parametric");
    model.study("std1").feature("param").setIndex("pname", "Rl", 0);
    model.study("std1").feature("param").setIndex("plistarr", "", 0);
    model.study("std1").feature("param").setIndex("punit", "m", 0);
    model.study("std1").feature("param").setIndex("pname", "Rl", 0);
    model.study("std1").feature("param").setIndex("plistarr", "", 0);
    model.study("std1").feature("param").setIndex("punit", "m", 0);
    model.study("std1").feature("param").setIndex("pname", "hf", 0);
    model.study("std1").feature("param").setIndex("plistarr", "0.5 1 2", 0);
    model.study("std1").feature("stat").setSolveFor("/physics/mfnc", false);
    model.study("std1").feature("stat").setSolveFor("/physics/mfncbe2", false);
    model.study("std1").feature("stat").setSolveFor("/multiphysics/msspc1", false);
    model.study("std1").createAutoSequences("all");

    model.sol().create("sol2");
    model.sol("sol2").study("std1");
    model.sol("sol2").label("\u53c2\u6570\u5316\u89e3 1");

    model.batch("p1").feature("so1").set("psol", "sol2");
    model.batch("p1").run("compute");

    model.result().dataset("dset1").selection().geom("geom1", 3);
    model.result().dataset("dset1").selection().geom("geom1", 3);
    model.result().dataset("dset1").selection().all();
    model.result().create("pg1", "PlotGroup3D");
    model.result("pg1").run();
    model.result("pg1")
         .label("\u9ea6\u514b\u65af\u97e6\u8868\u9762\u5e94\u529b\u5f20\u91cf\uff08BEM \u78c1\u5316\u68d2\uff09");
    model.result("pg1").set("data", "dset2");
    model.result("pg1").setIndex("looplevel", 1, 0);
    model.result("pg1").set("edges", false);
    model.result("pg1").selection().geom("geom1", 2);
    model.result("pg1").selection().named("sel2");
    model.result("pg1").create("surf1", "Surface");
    model.result("pg1").feature("surf1")
         .set("expr", "sqrt(mfncbe.nToutx_BEM_rod^2+mfncbe.nTouty_BEM_rod^2+mfncbe.nToutz_BEM_rod^2)");
    model.result("pg1").feature("surf1").set("descractive", true);
    model.result("pg1").feature("surf1")
         .set("descr", "\u9ea6\u514b\u65af\u97e6\u8868\u9762\u5e94\u529b\u5f20\u91cf\u7684\u6a21");
    model.result("pg1").run();
    model.result("pg1").run();
    model.result("pg1").create("surf2", "Surface");
    model.result("pg1").feature("surf2").set("expr", "0");
    model.result("pg1").feature("surf2").set("titletype", "none");
    model.result("pg1").feature("surf2").set("coloring", "uniform");
    model.result("pg1").feature("surf2").set("color", "black");
    model.result("pg1").feature("surf2").set("wireframe", true);
    model.result("pg1").run();
    model.result().create("pg2", "PlotGroup3D");
    model.result("pg2").run();
    model.result("pg2")
         .label("\u9ea6\u514b\u65af\u97e6\u8868\u9762\u5e94\u529b\u5f20\u91cf\uff08BEM \u63a2\u6d4b\u8868\u9762\uff09");
    model.result("pg2").set("view", "new");
    model.result("pg2").create("surf1", "Surface");
    model.result("pg2").feature("surf1")
         .set("expr", "sqrt(mfncbe.nToutx_BEM_probe^2+mfncbe.nTouty_BEM_probe^2+mfncbe.nToutz_BEM_probe^2)");
    model.result("pg2").feature("surf1").set("descractive", true);
    model.result("pg2").feature("surf1")
         .set("descr", "\u9ea6\u514b\u65af\u97e6\u8868\u9762\u5e94\u529b\u5f20\u91cf\u7684\u6a21");
    model.result("pg2").run();

    model.view("view3").set("showgrid", false);
    model.view("view3").set("showaxisorientation", false);

    model.result().numerical().create("gev1", "EvalGlobal");
    model.result().numerical("gev1").set("data", "dset2");
    model.result().numerical("gev1")
         .setIndex("expr", "sqrt(mfncbe.Tx_BEM_rod^2+(mfncbe.Ty_BEM_rod-1[N*m])^2+mfncbe.Tz_BEM_rod^2)", 0);
    model.result().numerical("gev1")
         .setIndex("descr", "\u78c1\u77e9\u8bef\u5dee\uff08BEM \u78c1\u5316\u68d2\uff09", 0);
    model.result().numerical("gev1")
         .setIndex("expr", "sqrt(mfncbe.Tx_BEM_probe^2+(mfncbe.Ty_BEM_probe-1[N*m])^2+mfncbe.Tz_BEM_probe^2)", 1);
    model.result().numerical("gev1")
         .setIndex("descr", "\u78c1\u77e9\u8bef\u5dee\uff08BEM \u63a2\u6d4b\u8868\u9762\uff09", 1);
    model.result().table().create("tbl1", "Table");
    model.result().table("tbl1").comments("\u5168\u5c40\u8ba1\u7b97 1");
    model.result().numerical("gev1").set("table", "tbl1");
    model.result().numerical("gev1").setResult();

    model.component("comp1").mesh().duplicate("mesh2", "mesh1");
    model.component("comp1").mesh("mesh2").run();

    model.component("comp1").view("view1").set("transparency", true);

    model.component("comp1").mesh("mesh2").create("ftet1", "FreeTet");
    model.component("comp1").mesh("mesh2").run();

    model.component("comp1").view("view1").set("transparency", false);

    model.study().create("std2");
    model.study("std2").create("stat", "Stationary");
    model.study("std2").feature("stat").setSolveFor("/physics/mfncbe", false);
    model.study("std2").feature("stat").setSolveFor("/physics/mfnc", true);
    model.study("std2").feature("stat").setSolveFor("/physics/mfncbe2", true);
    model.study("std2").feature("stat").setSolveFor("/multiphysics/msspc1", true);
    model.study("std2").setGenPlots(false);
    model.study("std2").create("param", "Parametric");
    model.study("std2").feature("param").setIndex("pname", "Rl", 0);
    model.study("std2").feature("param").setIndex("plistarr", "", 0);
    model.study("std2").feature("param").setIndex("punit", "m", 0);
    model.study("std2").feature("param").setIndex("pname", "Rl", 0);
    model.study("std2").feature("param").setIndex("plistarr", "", 0);
    model.study("std2").feature("param").setIndex("punit", "m", 0);
    model.study("std2").feature("param").setIndex("pname", "hf", 0);
    model.study("std2").feature("param").setIndex("plistarr", "0.5 1 2 3 4 5", 0);
    model.study("std2").createAutoSequences("all");

    model.sol().create("sol7");
    model.sol("sol7").study("std2");
    model.sol("sol7").label("\u53c2\u6570\u5316\u89e3 2");

    model.batch("p2").feature("so1").set("psol", "sol7");
    model.batch("p2").run("compute");

    model.result().dataset("dset3").selection().geom("geom1", 3);
    model.result().dataset("dset3").selection().geom("geom1", 3);
    model.result().dataset("dset3").selection().all();
    model.result().create("pg3", "PlotGroup3D");
    model.result("pg3").run();
    model.result("pg3")
         .label("\u9ea6\u514b\u65af\u97e6\u8868\u9762\u5e94\u529b\u5f20\u91cf\uff08FEM \u78c1\u5316\u68d2\uff09");
    model.result("pg3").set("data", "dset4");
    model.result("pg3").set("edges", false);
    model.result("pg3").selection().geom("geom1", 2);
    model.result("pg3").selection().named("sel2");
    model.result("pg3").create("surf1", "Surface");
    model.result("pg3").feature("surf1")
         .set("expr", "sqrt(mfnc.nToutx_FEM_rod^2+mfnc.nTouty_FEM_rod^2+mfnc.nToutz_FEM_rod^2)");
    model.result("pg3").feature("surf1").set("descractive", true);
    model.result("pg3").feature("surf1")
         .set("descr", "\u9ea6\u514b\u65af\u97e6\u8868\u9762\u5e94\u529b\u5f20\u91cf\u7684\u6a21");
    model.result("pg3").run();
    model.result("pg3").run();
    model.result("pg3").create("surf2", "Surface");
    model.result("pg3").feature("surf2").set("expr", "0");
    model.result("pg3").feature("surf2").set("titletype", "none");
    model.result("pg3").feature("surf2").set("coloring", "uniform");
    model.result("pg3").feature("surf2").set("color", "black");
    model.result("pg3").feature("surf2").set("wireframe", true);
    model.result("pg3").run();
    model.result().create("pg4", "PlotGroup3D");
    model.result("pg4").run();
    model.result("pg4")
         .label("\u9ea6\u514b\u65af\u97e6\u8868\u9762\u5e94\u529b\u5f20\u91cf\uff08FEM \u63a2\u6d4b\u8868\u9762\uff09");
    model.result("pg4").set("data", "dset3");
    model.result("pg4").set("view", "view3");
    model.result("pg4").create("surf1", "Surface");
    model.result("pg4").feature("surf1")
         .set("expr", "sqrt(mfnc.nToutx_FEM_probe^2+mfnc.nTouty_FEM_probe^2+mfnc.nToutz_FEM_probe^2)");
    model.result("pg4").feature("surf1").set("descractive", true);
    model.result("pg4").feature("surf1")
         .set("descr", "\u9ea6\u514b\u65af\u97e6\u8868\u9762\u5e94\u529b\u5f20\u91cf\u7684\u6a21");
    model.result("pg4").run();
    model.result().numerical().create("gev2", "EvalGlobal");
    model.result().numerical("gev2").set("data", "dset4");
    model.result().numerical("gev2")
         .setIndex("expr", "sqrt(mfnc.Tx_FEM_rod^2+(mfnc.Ty_FEM_rod-1[N*m])^2+mfnc.Tz_FEM_rod^2)", 0);
    model.result().numerical("gev2")
         .setIndex("descr", "\u78c1\u77e9\u8bef\u5dee\uff08FEM \u78c1\u5316\u68d2\uff09", 0);
    model.result().numerical("gev2")
         .setIndex("expr", "sqrt(mfnc.Tx_FEM_probe^2+(mfnc.Ty_FEM_probe-1[N*m])^2+mfnc.Tz_FEM_probe^2)", 1);
    model.result().numerical("gev2")
         .setIndex("descr", "\u78c1\u77e9\u8bef\u5dee\uff08FEM \u63a2\u6d4b\u8868\u9762\uff09", 1);
    model.result().table().create("tbl2", "Table");
    model.result().table("tbl2").comments("\u5168\u5c40\u8ba1\u7b97 2");
    model.result().numerical("gev2").set("table", "tbl2");
    model.result().numerical("gev2").setResult();
    model.result().create("pg5", "PlotGroup1D");
    model.result("pg5").run();
    model.result("pg5").label("\u78c1\u77e9\u8bef\u5dee");
    model.result("pg5").set("titletype", "manual");
    model.result("pg5").set("title", "\u78c1\u77e9\u7684\u7f51\u683c\u6536\u655b");
    model.result("pg5").set("xlabelactive", true);
    model.result("pg5").set("xlabel", "\u78c1\u6781\u5706\u89d2\u7684\u7f51\u683c\u6bd4\u4f8b\u56e0\u5b50 (hf)");
    model.result("pg5").set("ylabelactive", true);
    model.result("pg5").set("ylabel", "\u76f8\u5bf9\u4e8e\u89e3\u6790\u6a21\u578b\u7684\u8bef\u5dee");
    model.result("pg5").create("tblp1", "Table");
    model.result("pg5").feature("tblp1").set("markerpos", "datapoints");
    model.result("pg5").feature("tblp1").set("linewidth", "preference");
    model.result("pg5").feature("tblp1").set("linemarker", "cycle");
    model.result("pg5").feature("tblp1").set("legend", false);
    model.result("pg5").run();
    model.result("pg5").feature("tblp1").set("legend", true);
    model.result("pg5").run();
    model.result("pg5").create("tblp2", "Table");
    model.result("pg5").feature("tblp2").set("markerpos", "datapoints");
    model.result("pg5").feature("tblp2").set("linewidth", "preference");
    model.result("pg5").feature("tblp2").set("table", "tbl2");
    model.result("pg5").feature("tblp2").set("linemarker", "cycle");
    model.result("pg5").feature("tblp2").set("legend", true);
    model.result("pg5").run();
    model.result("pg5").set("ylog", true);
    model.result("pg1").run();
    model.result("pg1").run();

    model.component("comp1").view("view1").set("showgrid", true);
    model.component("comp1").view("view1").set("showaxisorientation", true);

    model.result("pg1").run();

    model.title("\u529b\u8ba1\u7b97 3 - \u78c1\u77e9 BEM FEM");

    model
         .description("\u672c\u6a21\u578b\u662f\u201c\u78c1\u529b BEM FEM\u201d\u9a8c\u8bc1\u6a21\u578b\u7684\u6269\u5c55\u6a21\u578b\u3002\u4e00\u6839\u957f\u5ea6\u4e3a 1 \u7c73\u7684\u78c1\u5316\u68d2\u653e\u7f6e\u5728\u5782\u76f4\u5916\u78c1\u573a\u4e2d\u3002\u5047\u8bbe\u4efb\u4f55\u4f4d\u7f6e\u7684\u76f8\u5bf9\u78c1\u5bfc\u7387\u5747\u4e3a 1\u3002\u901a\u8fc7\u9009\u5b9a\u5916\u78c1\u573a\u5f3a\u5ea6\uff0c\u4f7f\u89e3\u6790\u6a21\u578b\u53ef\u4ee5\u9884\u6d4b\u78c1\u5316\u68d2\u4e0a\u7684\u529b\u77e9\u6070\u597d\u4e3a 1 \u725b\u987f\u7c73\u3002\n\n\u672c\u4f8b\u91c7\u7528\u8fb9\u754c\u5143\u6cd5 (BEM) \u548c\u6709\u9650\u5143\u6cd5 (FEM) \u5bf9\u6a21\u578b\u5177\u6709\u591a\u79cd\u4e0d\u540c\u7f51\u683c\u5927\u5c0f\u7684\u60c5\u51b5\u8fdb\u884c\u5206\u6790\uff0c\u5e76\u63d0\u4f9b\u4eff\u771f\u7ed3\u679c\u4e0e\u89e3\u6790\u6a21\u578b\u7684\u6bd4\u8f83\u6570\u636e\uff0c\u68c0\u9a8c\u4e86\u78c1\u5316\u68d2\u78c1\u6781\u4e0a\u9ea6\u514b\u65af\u97e6\u8868\u9762\u5e94\u529b\u5f20\u91cf\u7684\u8d28\u91cf\uff0c\u5e76\u9488\u5bf9 BEM \u548c FEM \u4e24\u79cd\u65b9\u6cd5\u4f7f\u7528\u53c2\u6570\u5316\u626b\u63cf\u6765\u7814\u7a76\u7f51\u683c\u6536\u655b\u6027\u3002");

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
    model.sol("sol11").clearSolutionData();
    model.sol("sol12").clearSolutionData();
    model.sol("sol13").clearSolutionData();

    model.label("force_calculation_03_magnetic_torque_bem.mph");

    return model;
  }

  public static void main(String[] args) {
    run();
  }

}
