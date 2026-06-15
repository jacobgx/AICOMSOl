/*
 * biodegradation_mg_stent.java
 */

import com.comsol.model.*;
import com.comsol.model.util.*;

/** Model exported on Apr 30 2026, 09:44 by COMSOL 6.3.0.290. */
public class biodegradation_mg_stent {

  public static Model run() {
    Model model = ModelUtil.create("Model");

    model
         .modelPath("D:\\Program Files\\COMSOL\\COMSOL63\\Multiphysics\\applications\\Corrosion_Module\\General_Corrosion");

    model.component().create("comp1", true);

    model.component("comp1").geom().create("geom1", 3);
    model.component("comp1").geom("geom1").geomRep("comsol");

    model.component("comp1").mesh().create("mesh1");
    model.component("comp1").mesh("mesh1").contribute("geom/detail", true);

    model.component("comp1").physics().create("ls", "LevelSet", "geom1");

    model.study().create("std1");
    model.study("std1").create("phasei", "PhaseInitialization");
    model.study("std1").feature("phasei").set("ftplistmethod", "manual");
    model.study("std1").feature("phasei").set("solnum", "auto");
    model.study("std1").feature("phasei").set("notsolnum", "auto");
    model.study("std1").feature("phasei").set("outputmap", new String[]{});
    model.study("std1").feature("phasei").set("ngenAUX", "1");
    model.study("std1").feature("phasei").set("goalngenAUX", "1");
    model.study("std1").feature("phasei").set("ngenAUX", "1");
    model.study("std1").feature("phasei").set("goalngenAUX", "1");
    model.study("std1").feature("phasei").setSolveFor("/physics/ls", true);
    model.study("std1").create("time", "Transient");
    model.study("std1").feature("time").set("initstudy", "std1");
    model.study("std1").feature("time").set("notstudy", "std1");
    model.study("std1").feature("time").set("ftplistmethod", "manual");
    model.study("std1").feature("time").set("initialtime", "0");
    model.study("std1").feature("time").set("useinitsol", "on");
    model.study("std1").feature("time").set("usesol", "on");
    model.study("std1").feature("time").set("notsolmethod", "sol");
    model.study("std1").feature("time").set("outputmap", new String[]{});
    model.study("std1").feature("time").setSolveFor("/physics/ls", true);

//    To import content from file, use:
//    model.param().loadFile("FILENAME");
    model.param().set("Vn", "2[mm/year]", "Mg \u6eb6\u89e3\u901f\u5ea6");
    model.param().set("u_max", "2*u_avg", "\u8840\u7ba1\u4e2d\u7684\u6700\u5927\u901f\u5ea6");
    model.param().set("u_avg", "1[mm/s]", "\u8840\u7ba1\u4e2d\u7684\u5e73\u5747\u901f\u5ea6");
    model.param().set("t_param", "1[d]", "\u7814\u7a76 2 \u7684\u65f6\u95f4\u53c2\u6570");
    model.param().set("S_Mg", "Vn/MMg*rhoMg", "Mg \u6e90\u9879");
    model.param().set("rhoMg", "1735e-6[g/mm^3]", "Mg \u5bc6\u5ea6");
    model.param().set("R_vessel", "0.7[mm]", "\u8840\u7ba1\u534a\u5f84");
    model.param().set("R_tissue", "1.2[mm]", "\u8840\u7ba1+\u7ec4\u7ec7\u7684\u603b\u534a\u5f84");
    model.param().set("MMg", "24.305 [g/mol]", "Mg \u6469\u5c14\u8d28\u91cf");
    model.param().set("hstent", "1E-5", "\u652f\u67b6\u8868\u9762\u7684\u7f51\u683c\u5355\u5143\u5927\u5c0f");
    model.param().set("epsl_tissue", "0.3", "\u7ec4\u7ec7\u4e2d\u7684\u8840\u6d46\u4f53\u79ef\u5206\u6570");
    model.param().set("epsl_blood", "0.6", "\u8840\u7ba1\u4e2d\u7684\u8840\u6d46\u4f53\u79ef\u5206\u6570");
    model.param().set("DMg", "0.7e-5[cm^2/s]", "Mg \u79bb\u5b50\u6269\u6563\u7cfb\u6570");

    model.component("comp1").geom("geom1").create("imp1", "Import");
    model.component("comp1").geom("geom1").feature("imp1").set("filename", "biodegradation_mg_stent.mphbin");
    model.component("comp1").geom("geom1").feature("imp1").importData();
    model.component("comp1").geom("geom1").create("wp1", "WorkPlane");
    model.component("comp1").geom("geom1").feature("wp1").set("unite", true);
    model.component("comp1").geom("geom1").feature("wp1").set("quickplane", "yz");
    model.component("comp1").geom("geom1").feature("wp1").set("quickx", "-5.5 [mm]");
    model.component("comp1").geom("geom1").feature("wp1").geom().create("c1", "Circle");
    model.component("comp1").geom("geom1").feature("wp1").geom().feature("c1").set("r", "R_tissue");
    model.component("comp1").geom("geom1").feature("wp1").geom().feature("c1").set("angle", 30);
    model.component("comp1").geom("geom1").feature("wp1").geom().feature("c1").set("rot", 90);
    model.component("comp1").geom("geom1").feature("wp1").geom().run("c1");
    model.component("comp1").geom("geom1").feature("wp1").geom().create("c2", "Circle");
    model.component("comp1").geom("geom1").feature("wp1").geom().feature("c2").set("r", "R_vessel");
    model.component("comp1").geom("geom1").feature("wp1").geom().feature("c2").set("angle", 30);
    model.component("comp1").geom("geom1").feature("wp1").geom().feature("c2").set("rot", 90);
    model.component("comp1").geom("geom1").run("wp1");
    model.component("comp1").geom("geom1").feature().create("ext1", "Extrude");
    model.component("comp1").geom("geom1").feature("ext1").setIndex("distance", "5.5[mm]", 0);

    model.component("comp1").view("view1").set("renderwireframe", true);

    model.component("comp1").geom("geom1").runPre("fin");

    model.component("comp1").cpl().create("intop1", "Integration");

    model.component("comp1").geom("geom1").run();

    model.component("comp1").cpl("intop1").set("axisym", true);
    model.component("comp1").cpl("intop1").selection().all();
    model.component("comp1").cpl().create("maxop1", "Maximum");
    model.component("comp1").cpl("maxop1").selection().all();

    model.component("comp1").probe().create("var1", "GlobalVariable");
    model.component("comp1").probe("var1").set("probename", "stent_mass_loss");
    model.component("comp1").probe("var1").set("expr", "1-intop1(phils>0.5)/at(0,intop1(phils>0.5))");
    model.component("comp1").probe("var1").set("unit", "%");
    model.component("comp1").probe("var1").set("descractive", true);

    model.component("comp1").selection().create("sel1", "Explicit");
    model.component("comp1").selection("sel1").set(2);
    model.component("comp1").selection().create("sel2", "Explicit");
    model.component("comp1").selection("sel2").set(1);
    model.component("comp1").selection().create("sel3", "Explicit");
    model.component("comp1").selection("sel3").set(3);
    model.component("comp1").selection().create("uni1", "Union");
    model.component("comp1").selection("uni1").set("input", new String[]{"sel2", "sel3"});

    model.component("comp1").physics("ls").feature("lsm1").set("gamma", "max(maxop1(Vn),eps)");
    model.component("comp1").physics("ls").feature("lsm1").set("epsilon_ls", "hstent");
    model.component("comp1").physics("ls").feature("lsm1")
         .set("u", new String[]{"Vn*ls.intnormx", "Vn*ls.intnormy", "Vn*ls.intnormz"});
    model.component("comp1").physics("ls").feature("initfluid2").selection().named("sel3");

    model.component("comp1").mesh("mesh1").autoMeshSize(9);
    model.component("comp1").mesh("mesh1").automatic(false);
    model.component("comp1").mesh("mesh1").feature("size1").set("hminactive", false);
    model.component("comp1").mesh("mesh1").create("ftri1", "FreeTri");
    model.component("comp1").mesh("mesh1").feature("ftri1").selection().set(11);
    model.component("comp1").mesh("mesh1").feature("ftri1").create("size1", "Size");
    model.component("comp1").mesh("mesh1").feature("ftri1").feature("size1").set("custom", true);
    model.component("comp1").mesh("mesh1").feature("ftri1").feature("size1").set("hmaxactive", true);
    model.component("comp1").mesh("mesh1").feature("ftri1").feature("size1").set("hmax", "hstent");
    model.component("comp1").mesh("mesh1").feature("ftri1").feature("size1").set("hminactive", true);
    model.component("comp1").mesh("mesh1").feature("ftri1").feature("size1").set("hmin", "1e-6");
    model.component("comp1").mesh("mesh1").create("swe1", "Sweep");
    model.component("comp1").mesh("mesh1").feature("swe1").selection().geom("geom1", 3);
    model.component("comp1").mesh("mesh1").feature("swe1").selection().named("sel3");
    model.component("comp1").mesh("mesh1").feature("swe1").create("dis1", "Distribution");
    model.component("comp1").mesh("mesh1").feature("swe1").feature("dis1").set("numelem", 10);
    model.component("comp1").mesh("mesh1").feature().move("ftri1", 2);
    model.component("comp1").mesh("mesh1").feature().move("swe1", 3);
    model.component("comp1").mesh("mesh1").create("bl1", "BndLayer");
    model.component("comp1").mesh("mesh1").feature("bl1").create("blp", "BndLayerProp");
    model.component("comp1").mesh("mesh1").feature("bl1").selection().geom("geom1", 3);
    model.component("comp1").mesh("mesh1").feature("bl1").selection().named("sel1");
    model.component("comp1").mesh("mesh1").feature("bl1").feature("blp").selection().set(6, 12, 17, 23, 31, 37);
    model.component("comp1").mesh("mesh1").feature("bl1").feature("blp").set("blnlayers", 3);
    model.component("comp1").mesh("mesh1").feature("bl1").feature("blp").set("inittype", "blhmin");
    model.component("comp1").mesh("mesh1").feature("bl1").feature("blp").set("blhmin", "hstent/10");
    model.component("comp1").mesh("mesh1").run();

    model.study("std1").feature("phasei").set("probesel", "none");
    model.study("std1").feature("time").set("tunit", "d");
    model.study("std1").feature("time").set("tlist", "range(0,1,5)");
    model.study("std1").showAutoSequences("all");

    model.sol("sol1").feature("t1").set("tstepsbdf", "strict");

    model.study("std1").createAutoSequences("all");

    model.component("comp1").probe("var1").genResult("none");

    model.sol("sol1").runAll();

    model.result().create("pg2", "PlotGroup3D");
    model.result("pg2").label("\u6d41\u4f53 1 \u7684\u4f53\u79ef\u5206\u6570 (ls)");
    model.result("pg2").set("frametype", "spatial");
    model.result("pg2").feature().create("slc1", "Slice");
    model.result("pg2").feature("slc1").set("smooth", "internal");
    model.result("pg2").feature("slc1").set("data", "parent");
    model.result("pg2").feature().create("iso1", "Isosurface");
    model.result("pg2").feature("iso1").set("levelmethod", "levels");
    model.result("pg2").feature("iso1").set("levels", "0.5");
    model.result("pg2").feature("iso1").set("coloring", "uniform");
    model.result("pg2").feature("iso1").set("colorlegend", false);
    model.result("pg2").feature("iso1").set("color", "gray");
    model.result("pg2").feature("iso1").set("smooth", "none");
    model.result("pg2").feature("iso1").set("data", "parent");
    model.result("pg2").run();
    model.result("pg1").set("window", "window1");
    model.result("pg1").run();
    model.result("pg1").set("showlegends", false);
    model.result("pg1").set("window", "window1");
    model.result("pg1").run();
    model.result("pg2").run();
    model.result("pg2").set("edges", false);
    model.result("pg2").set("showlegends", false);
    model.result("pg2").run();
    model.result("pg2").feature("slc1").active(false);
    model.result("pg2").run();
    model.result("pg2").feature("iso1").set("color", "blue");
    model.result("pg2").run();
    model.result("pg2").setIndex("looplevel", 2, 0);
    model.result("pg2").run();
    model.result("pg2").setIndex("looplevel", 5, 0);
    model.result("pg2").run();
    model.result().dataset().create("mir1", "Mirror3D");
    model.result().dataset("mir1").set("quickplane", "zx");
    model.result().dataset().create("sec1", "Sector3D");
    model.result().dataset("sec1").set("data", "mir1");
    model.result().dataset("sec1").setIndex("genpoints", 1, 1, 0);
    model.result().dataset("sec1").setIndex("genpoints", 0, 1, 2);
    model.result().dataset("sec1").set("sectors", 6);
    model.result().create("pg3", "PlotGroup3D");
    model.result("pg3").run();
    model.result("pg3").set("data", "sec1");
    model.result("pg3").create("iso1", "Isosurface");
    model.result("pg3").feature("iso1").set("levelmethod", "levels");
    model.result("pg3").feature("iso1").set("levels", 0.5);
    model.result("pg3").feature("iso1").set("coloring", "uniform");
    model.result("pg3").feature("iso1").set("color", "blue");
    model.result("pg3").run();
    model.result("pg3").setIndex("looplevel", 2, 0);
    model.result("pg3").set("edges", false);
    model.result("pg3").set("showlegends", false);

    model.view("view3").set("showgrid", false);
    model.view("view3").set("showaxisorientation", false);

    model.result("pg3").run();
    model.result("pg3").setIndex("looplevel", 5, 0);
    model.result("pg3").run();

    model.component("comp1").physics().create("el", "ElectrophoreticTransport", "geom1");

    model.study("std1").feature("phasei").setSolveFor("/physics/el", false);
    model.study("std1").feature("time").setSolveFor("/physics/el", false);

    model.param().create("par2");

//    To import content from file, use:
//    model.param("par2").loadFile("FILENAME");
    model.param("par2").set("Ka", "10^-3.49*1[M]", "\u9178\u5e73\u8861\u5e38\u6570\uff0cH2CO3<->H + HCO3");
    model.param("par2").set("KD", "715", "CO2 \u6c34\u5408\u5e73\u8861\u5e38\u6570");
    model.param("par2").set("pKa_H2CO3_1", "-log10((Ka/1[M])/KD)", "pKa\uff0c\u7b2c\u4e00\u6eb6\u89e3\u6b65");
    model.param("par2").set("pKa_H2CO3_2", "10.3", "pKa\uff0c\u7b2c\u4e8c\u6eb6\u89e3\u6b65");
    model.param("par2").set("pKa_H3PO4_1", "-log10(7.5e-3)", "pKa\uff0c\u7b2c\u4e00\u6eb6\u89e3\u6b65");
    model.param("par2").set("pKa_H3PO4_2", "-log10(6.2e-8)", "pKa\uff0c\u7b2c\u4e8c\u6eb6\u89e3\u6b65");
    model.param("par2")
         .set("K_H3PO4_1", "10^-pKa_H3PO4_1*1[M]", "\u9178\u5e73\u8861\u5e38\u6570\uff0c\u7b2c\u4e00\u6eb6\u89e3\u6b65");
    model.param("par2")
         .set("K_H3PO4_2", "10^-pKa_H3PO4_2*1[M]", "\u9178\u5e73\u8861\u5e38\u6570\uff0c\u7b2c\u4e8c\u6eb6\u89e3\u6b65");
    model.param("par2")
         .set("K_H2CO3_1", "10^-pKa_H2CO3_1*1[M]", "\u9178\u5e73\u8861\u5e38\u6570\uff0c\u7b2c\u4e00\u6eb6\u89e3\u6b65");
    model.param("par2")
         .set("K_H2CO3_2", "10^-pKa_H2CO3_2*1[M]", "\u9178\u5e73\u8861\u5e38\u6570\uff0c\u7b2c\u4e8c\u6eb6\u89e3\u6b65");
    model.param("par2").set("pH0", "7.4", "\u5165\u53e3 pH");
    model.param("par2").set("cH0", "(10^-pH0)*1[M]", "\u5165\u53e3\u6d53\u5ea6\uff0cH");
    model.param("par2").set("cMg0", "1.5[mM]", "\u5165\u53e3\u6d53\u5ea6\uff0cMg");
    model.param("par2").set("pKw", "14.94-0.04209*(-273.15+T)/1[K]+1.718E-4*(-273.15+T)^2/1[K^2]", "pKw");
    model.param("par2").set("Kw", "(10^-pKw)*(1[M])^2", "\u6c34\u81ea\u89e3\u5e73\u8861\u5e38\u6570");
    model.param("par2").set("cOH0", "Kw/cH0", "\u5165\u53e3\u6d53\u5ea6\uff0cOH");
    model.param("par2").set("cHCO30", "25[mM]", "\u5165\u53e3\u6d53\u5ea6\uff0cHCO3");
    model.param("par2").set("cCO20", "cH0*cHCO30/K_H2CO3_1", "\u5165\u53e3\u6d53\u5ea6\uff0cCO2(aq)");
    model.param("par2").set("cCO30", "cHCO30*K_H2CO3_2/cH0", "\u5165\u53e3\u6d53\u5ea6\uff0cCO3");
    model.param("par2").set("cCO2tot", "cHCO30+cCO20+cCO30", "CO2 \u7269\u8d28\u7684\u603b\u6d53\u5ea6");
    model.param("par2").set("cHPO40", "1[mM]", "\u5165\u53e3\u6d53\u5ea6\uff0cPO4");
    model.param("par2").set("cH2PO40", "cHPO40*cH0/K_H3PO4_2", "\u5165\u53e3\u6d53\u5ea6\uff0cH2PO4");
    model.param("par2").set("cH3PO40", "cH2PO40*cH0/K_H3PO4_1", "\u5165\u53e3\u6d53\u5ea6\uff0cH3PO4");
    model.param("par2").set("cH3PO4tot", "cHPO40+cH2PO40+cH3PO40", "H3PO4 \u7269\u8d28\u7684\u603b\u6d53\u5ea6");
    model.param("par2")
         .set("cAux0", "-(2*cMg0+cH0-cH2PO40-2*cHPO40-cHCO30-2*cCO30-cOH0)", "\u5269\u4f59\u79bb\u5b50\u7684\u5165\u53e3\u6d53\u5ea6");
    model.param("par2").set("T", "37[degC]", "\u6e29\u5ea6");

    model.component("comp1").variable().create("var1");
    model.component("comp1").variable("var1").selection().geom("geom1", 3);
    model.component("comp1").variable("var1").selection().named("sel1");

//    To import content from file, use:
//    model.component("comp1").variable("var1").loadFile("FILENAME");
    model.component("comp1").variable("var1").set("r", "sqrt(z^2+y^2)", "\u8840\u7ba1\u5f84\u5411\u5750\u6807");
    model.component("comp1").variable("var1").set("u", "u_max*(1-r^2/R_vessel^2)", "Hagen-Poiseuille \u901f\u5ea6");
    model.component("comp1").variable("var1")
         .set("epsl", "epsl_blood*max(1-phils,1e-3)", "\u8840\u6d46\u4f53\u79ef\u5206\u6570");
    model.component("comp1").variable().create("var2");
    model.component("comp1").variable("var2").selection().geom("geom1", 3);
    model.component("comp1").variable("var2").selection().named("uni1");

//    To import content from file, use:
//    model.component("comp1").variable("var2").loadFile("FILENAME");
    model.component("comp1").variable("var2").set("u", "0", "\u901f\u5ea6");
    model.component("comp1").variable("var2")
         .set("epsl", "epsl_tissue*max(1-phils,1e-3)", "\u8840\u6d46\u4f53\u79ef\u5206\u6570");

    model.component("comp1").physics("el").prop("TransportMechanism").set("Convection", true);
    model.component("comp1").physics("el").prop("TransportMechanism").set("MassTransferInPorousMedia", true);
    model.component("comp1").physics("el").feature("sol1").set("u", new String[]{"u", "0", "0"});
    model.component("comp1").physics("el").feature("sol1").set("SetType", "Diffusivity");
    model.component("comp1").physics("el").create("wa1", "WeakAcid", 3);
    model.component("comp1").physics("el").feature("wa1").set("speciesname", "H2CO3");
    model.component("comp1").physics("el").feature("wa1").set("ChemicalType", "Polyprotic");
    model.component("comp1").physics("el").feature("wa1").setIndex("pKa", "pKa_H2CO3_1", 0, 0);
    model.component("comp1").physics("el").feature("wa1").setIndex("pKa", "pKa_H2CO3_2", 1, 0);
    model.component("comp1").physics("el").feature("wa1").set("ImmobileSpecies", true);
    model.component("comp1").physics("el").feature("wa1").set("c", "cCO2tot");
    model.component("comp1").physics("el").create("wa2", "WeakAcid", 3);
    model.component("comp1").physics("el").feature("wa2").set("speciesname", "H3PO4");
    model.component("comp1").physics("el").feature("wa2").set("ChemicalType", "Polyprotic");
    model.component("comp1").physics("el").feature("wa2").setIndex("pKa", "pKa_H3PO4_1", 0, 0);
    model.component("comp1").physics("el").feature("wa2").setIndex("pKa", "pKa_H3PO4_2", 1, 0);
    model.component("comp1").physics("el").feature("wa2").set("ImmobileSpecies", true);
    model.component("comp1").physics("el").feature("wa2").set("c", "cH3PO4tot");
    model.component("comp1").physics("el").create("fds1", "FullyDissociatedSpecies", 3);
    model.component("comp1").physics("el").feature("fds1").set("speciesname", "Mg");
    model.component("comp1").physics("el").feature("fds1").set("z", 2);
    model.component("comp1").physics("el").feature("fds1").set("SetType", "Diffusivity");
    model.component("comp1").physics("el").feature("fds1").set("D", "DMg");
    model.component("comp1").physics("el").feature("fds1").feature("initc1").set("initc", "cMg0");
    model.component("comp1").physics("el").feature("fds1").create("ss1", "SpeciesSource", 3);
    model.component("comp1").physics("el").feature("fds1").feature("ss1").set("S", "S_Mg*ls.delta");
    model.component("comp1").physics("el").feature("fds1").create("in1", "Inflow", 2);
    model.component("comp1").physics("el").feature("fds1").feature("in1").selection().set(4);
    model.component("comp1").physics("el").feature("fds1").feature("in1").set("c0", "cMg0");
    model.component("comp1").physics("el").feature("fds1").feature("in1")
         .set("BoundaryConditionType", "FluxDanckwerts");
    model.component("comp1").physics("el").feature("fds1").create("out1", "Outflow", 2);
    model.component("comp1").physics("el").feature("fds1").feature("out1").selection().set(44);
    model.component("comp1").physics("el").create("fds2", "FullyDissociatedSpecies", 3);
    model.component("comp1").physics("el").feature("fds2").set("speciesname", "aux");
    model.component("comp1").physics("el").feature("fds2").set("z", 1);
    model.component("comp1").physics("el").feature("fds2").set("ImmobileSpecies", true);
    model.component("comp1").physics("el").feature("fds2").set("c", "cAux0");
    model.component("comp1").physics("el").create("por1", "Porosity", 3);
    model.component("comp1").physics("el").feature("por1").set("epsilon_p", "epsl");

    model.common("cminpt").set("modified", new String[][]{{"temperature", "T"}});

    model.study().create("std2");
    model.study("std2").create("stat", "Stationary");
    model.study("std2").feature("stat").setSolveFor("/physics/ls", false);
    model.study("std2").feature("stat").setSolveFor("/physics/el", true);
    model.study("std2").create("param", "Parametric");
    model.study("std2").feature("param").setIndex("pname", "cAux0", 0);
    model.study("std2").feature("param").setIndex("plistarr", "", 0);
    model.study("std2").feature("param").setIndex("punit", "mol/m^3", 0);
    model.study("std2").feature("param").setIndex("pname", "cAux0", 0);
    model.study("std2").feature("param").setIndex("plistarr", "", 0);
    model.study("std2").feature("param").setIndex("punit", "mol/m^3", 0);
    model.study("std2").feature("param").setIndex("pname", "t_param", 0);
    model.study("std2").feature("param").setIndex("plistarr", "range(0,1,5)", 0);
    model.study("std2").feature("param").setIndex("punit", "d", 0);
    model.study("std2").feature("param").set("probesel", "none");
    model.study("std2").feature("stat").set("usesol", true);
    model.study("std2").feature("stat").set("notsolmethod", "sol");
    model.study("std2").feature("stat").set("notstudy", "std1");
    model.study("std2").feature("stat").set("notsolnum", "interp");
    model.study("std2").feature("stat").set("nott", "t_param");
    model.study("std2").showAutoSequences("all");

    model.sol("sol3").feature("v1").set("control", "user");
    model.sol("sol3").feature("v1").feature("comp1_phil").set("solvefor", false);

    model.study("std2").setGenPlots(false);
    model.study("std2").createAutoSequences("all");

    model.sol().create("sol4");
    model.sol("sol4").study("std2");
    model.sol("sol4").label("\u53c2\u6570\u5316\u89e3 1");

    model.batch("p1").feature("so1").set("psol", "sol4");
    model.batch("p1").run("compute");

    model.result().dataset().create("mir2", "Mirror3D");
    model.result().dataset("mir2").set("data", "dset5");
    model.result().dataset("mir2").set("quickplane", "zx");
    model.result().dataset().create("sec2", "Sector3D");
    model.result().dataset("sec2").set("data", "mir2");
    model.result().dataset("sec2").setIndex("genpoints", 1, 1, 0);
    model.result().dataset("sec2").setIndex("genpoints", 0, 1, 2);
    model.result().dataset("sec2").set("sectors", 6);
    model.result().dataset("sec2").set("include", "manual");
    model.result().dataset("sec2").set("startsector", -3);
    model.result().dataset("sec2").set("sectorsinclude", 3);
    model.result().create("pg4", "PlotGroup3D");
    model.result("pg4").run();
    model.result("pg4").set("data", "sec2");
    model.result("pg4").setIndex("looplevel", 2, 0);
    model.result("pg4").set("edges", false);
    model.result("pg4").create("vol1", "Volume");
    model.result("pg4").feature("vol1").set("expr", "el.c_Mg");
    model.result("pg4").feature("vol1").set("descr", "\u6d53\u5ea6");
    model.result("pg4").feature("vol1").set("colortable", "Prism");
    model.result("pg4").feature("vol1").create("tran1", "Transparency");
    model.result("pg4").run();
    model.result("pg4").run();
    model.result("pg4").feature("vol1").create("filt1", "Filter");
    model.result("pg4").run();
    model.result("pg4").feature("vol1").feature("filt1").set("expr", "ls.Vf1>=0.5");
    model.result("pg4").run();
    model.result("pg4").create("arwv1", "ArrowVolume");
    model.result("pg4").feature("arwv1").set("expr", new String[]{"u", "0", "0"});
    model.result("pg4").feature("arwv1").set("descractive", true);
    model.result("pg4").feature("arwv1").set("xnumber", 3);
    model.result("pg4").run();

    model.view("view4").set("showaxisorientation", false);
    model.view("view4").set("showgrid", false);

    model.result("pg4").run();
    model.result("pg4").setIndex("looplevel", 5, 0);
    model.result("pg4").run();
    model.result().duplicate("pg5", "pg4");
    model.result("pg5").run();
    model.result("pg5").run();
    model.result("pg5").feature("vol1").set("expr", "el.pH");
    model.result("pg5").run();
    model.result("pg5").setIndex("looplevel", 2, 0);
    model.result("pg5").run();
    model.result("pg5").setIndex("looplevel", 5, 0);
    model.result("pg5").run();

    model.title("\u9541\u5408\u91d1\u652f\u67b6\u7684\u751f\u7269\u964d\u89e3");

    model
         .description("\u672c\u4f8b\u6a21\u62df\u653e\u7f6e\u5728\u8840\u7ba1\u4e2d\u7684\u9541\u5408\u91d1 (Mg) \u652f\u67b6\u7684\u6eb6\u89e3\u8fc7\u7a0b\u3002\n\n\u5728\u672c\u6559\u7a0b\u7684\u7b2c\u4e00\u4e2a\u7814\u7a76\u4e2d\uff0c\u4f7f\u7528\u201c\u6c34\u5e73\u96c6\u201d\u63a5\u53e3\u6765\u6a21\u62df\u652f\u67b6\u5728\u6052\u5b9a\u5c40\u90e8\u6eb6\u89e3\u901f\u7387\u4f5c\u7528\u4e0b\u968f\u65f6\u95f4\u7684\u51e0\u4f55\u53d8\u5316\u3002\n\n\u63a5\u7740\uff0c\u5728\u7b2c\u4e8c\u4e2a\u7814\u7a76\u4e2d\uff0c\u4f7f\u7528\u6eb6\u89e3\u8fc7\u7a0b\u4e2d\u4e0d\u540c\u9636\u6bb5\u7684\u652f\u67b6\u5f62\u72b6\uff0c\u8ba1\u7b97\u652f\u67b6\u5468\u56f4\u7ec4\u7ec7\u548c\u8840\u7ba1\u4e2d\u9541\u79bb\u5b50\u7684\u6d53\u5ea6\u53ca\u76f8\u5e94\u7684 pH \u6c34\u5e73\u3002");

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

    model.label("biodegradation_mg_stent.mph");

    return model;
  }

  public static void main(String[] args) {
    run();
  }

}
