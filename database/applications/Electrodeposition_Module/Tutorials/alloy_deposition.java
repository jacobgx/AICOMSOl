/*
 * alloy_deposition.java
 */

import com.comsol.model.*;
import com.comsol.model.util.*;

/** Model exported on Apr 30 2026, 09:56 by COMSOL 6.3.0.290. */
public class alloy_deposition {

  public static Model run() {
    Model model = ModelUtil.create("Model");

    model
         .modelPath("D:\\Program Files\\COMSOL\\COMSOL63\\Multiphysics\\applications\\Electrodeposition_Module\\Tutorials");

    model.component().create("comp1", true);

    model.component("comp1").geom().create("geom1", 1);

    model.component("comp1").mesh().create("mesh1");

    model.component("comp1").physics().create("tcd", "TertiaryCurrentDistributionNernstPlanck", "geom1");
    model.component("comp1").physics("tcd").prop("SpeciesProperties").set("ChargeTransportModel", "WaterBased");
    model.component("comp1").physics("tcd").field("concentration").field("cNi");
    model.component("comp1").physics("tcd").field("concentration")
         .component(new String[]{"cNi", "cH3PO2", "cSO4", "cNa"});

    model.study().create("std1");
    model.study("std1").create("stat", "Stationary");
    model.study("std1").feature("stat").setSolveFor("/physics/tcd", true);

//    To import content from file, use:
//    model.param().loadFile("FILENAME");
    model.param().set("a", "0.51023", "\u901f\u5ea6\u8868\u8fbe\u5f0f\u4e2d\u7684\u6570\u503c\u5e38\u6570");
    model.param().set("aNi_ref", "0.5", "Ni \u9540\u5c42\u7684\u53c2\u8003\u6d3b\u6027");
    model.param().set("aP_ref", "0.5", "P \u9540\u5c42\u7684\u53c2\u8003\u6d3b\u6027");
    model.param().set("cH_ref", "0.1[M]", "H \u7684\u53c2\u8003\u6d53\u5ea6");
    model.param().set("cH0", "cH_ref", "H \u7684\u521d\u59cb\u6d53\u5ea6");
    model.param().set("cH3PO2_ref", "0.38[M]", "H3PO2 \u7684\u53c2\u8003\u6d53\u5ea6");
    model.param().set("cH3PO20", "cH3PO2_ref", "H3PO2 \u7684\u521d\u59cb\u6d53\u5ea6");
    model.param().set("cNa0", "0.3[M]", "Na \u7684\u521d\u59cb\u6d53\u5ea6");
    model.param().set("cNi_ref", "0.38[M]", "Ni \u7684\u53c2\u8003\u6d53\u5ea6");
    model.param().set("cNi0", "cNi_ref", "Ni \u7684\u521d\u59cb\u6d53\u5ea6");
    model.param().set("cSO40", "0.58[M]", "SO4 \u7684\u521d\u59cb\u6d53\u5ea6");
    model.param().set("DH", "9.312e-5[cm^2/s]", "H \u7684\u6269\u6563\u7cfb\u6570");
    model.param().set("DH3PO2", "1.54e-5[cm^2/s]", "H3PO3 \u7684\u6269\u6563\u7cfb\u6570");
    model.param().set("DNa", "1.334e-5[cm^2/s]", "Na \u7684\u6269\u6563\u7cfb\u6570");
    model.param().set("DNi", "0.71e-5[cm^2/s]", "Ni \u7684\u6269\u6563\u7cfb\u6570");
    model.param().set("DOH", "5.26e-5[cm^2/s]", "OH \u7684\u6269\u6563\u7cfb\u6570");
    model.param().set("DSO4", "1.065e-5[cm^2/s]", "SO4 \u7684\u6269\u6563\u7cfb\u6570");
    model.param().set("E_app", "-0.5[V]", "\u5916\u52a0\u7535\u52bf");
    model.param()
         .set("Eeq_ref_H", "0[V]+R_const*T/F_const*log(cH_ref/(1[M]))", "\u53c2\u8003\u5e73\u8861\u7535\u4f4d\uff0c\u6790\u6c22");
    model.param()
         .set("Eeq_ref_Ni", "-0.23[V]+R_const*T/(2*F_const)*log(cNi_ref/(1[M]))", "\u53c2\u8003\u5e73\u8861\u7535\u4f4d\uff0c\u954d\u6c89\u79ef");
    model.param()
         .set("Eeq_ref_P", "-0.51[V]+R_const*T/F_const*log(cH3PO2_ref*cH_ref/(1[M])^2)", "\u53c2\u8003\u5e73\u8861\u7535\u4f4d\uff0c\u78f7\u6c89\u79ef");
    model.param()
         .set("i0_ref_H", "6.3e-10[A/cm^2]", "\u53c2\u8003\u4ea4\u6362\u7535\u6d41\u5bc6\u5ea6\uff0c\u6790\u6c22");
    model.param()
         .set("i0_ref_Ni", "1e-8[A/cm^2]", "\u53c2\u8003\u4ea4\u6362\u7535\u6d41\u5bc6\u5ea6\uff0c\u954d\u6c89\u79ef");
    model.param()
         .set("i0_ref_P", "1e-4[A/cm^2]", "\u53c2\u8003\u4ea4\u6362\u7535\u6d41\u5bc6\u5ea6\uff0c\u78f7\u6c89\u79ef");
    model.param().set("M_Ni", "58.60[g/mol]", "\u954d\u7684\u6469\u5c14\u8d28\u91cf");
    model.param().set("M_P", "123.88[g/mol]", "\u78f7\u7684\u6469\u5c14\u8d28\u91cf");
    model.param().set("nu", "0.0123[cm^2/s]", "\u8fd0\u52a8\u9ecf\u5ea6");
    model.param().set("omega", "209.44 [rad/s]", "\u8f6c\u901f");
    model.param().set("rho_Ni", "8908[kg/m^3]", "\u954d\u7684\u5bc6\u5ea6");
    model.param().set("rho_P", "1820[kg/m^3]", "\u78f7\u7684\u5bc6\u5ea6");
    model.param().set("T", "298.15[K]", "\u6e29\u5ea6");

    model.component("comp1").geom("geom1").create("i1", "Interval");
    model.component("comp1").geom("geom1").feature("i1").setIndex("coord", "0.02[cm]", 1);
    model.component("comp1").geom("geom1").runPre("fin");

    model.component("comp1").variable().create("var1");

    model.component("comp1").geom("geom1").run();

//    To import content from file, use:
//    model.component("comp1").variable("var1").loadFile("FILENAME");
    model.component("comp1").variable("var1").set("vx", "-a*omega*(omega/nu)^(1/2)*x^2", "\u6d41\u4f53\u901f\u5ea6");
    model.component("comp1").variable("var1").set("xP", "1-xNi", "P \u7684\u6c89\u79ef\u6469\u5c14\u5206\u6570");
    model.component("comp1").variable("var1")
         .set("xNi_expr", "tcd.R_Ni/(tcd.R_Ni+tcd.R_P)", "xNi \u7684\u5206\u6570\u6c89\u79ef\u8868\u8fbe\u5f0f");

    model.component("comp1").physics("tcd").feature("sp1").setIndex("z", 2, 0);
    model.component("comp1").physics("tcd").feature("sp1").setIndex("z", -2, 2);
    model.component("comp1").physics("tcd").feature("sp1").setIndex("z", 1, 3);
    model.component("comp1").physics("tcd").feature("ice1").set("u", new String[]{"vx", "0", "0"});
    model.component("comp1").physics("tcd").feature("ice1")
         .set("D_cNi", new String[]{"DNi", "0", "0", "0", "DNi", "0", "0", "0", "DNi"});
    model.component("comp1").physics("tcd").feature("ice1")
         .set("D_cH3PO2", new String[]{"DH3PO2", "0", "0", "0", "DH3PO2", "0", "0", "0", "DH3PO2"});
    model.component("comp1").physics("tcd").feature("ice1")
         .set("D_cSO4", new String[]{"DSO4", "0", "0", "0", "DSO4", "0", "0", "0", "DSO4"});
    model.component("comp1").physics("tcd").feature("ice1")
         .set("D_cNa", new String[]{"DNa", "0", "0", "0", "DNa", "0", "0", "0", "DNa"});
    model.component("comp1").physics("tcd").feature("ice1")
         .set("DH", new String[]{"DH", "0", "0", "0", "DH", "0", "0", "0", "DH"});
    model.component("comp1").physics("tcd").feature("ice1")
         .set("DOH", new String[]{"DOH", "0", "0", "0", "DOH", "0", "0", "0", "DOH"});
    model.component("comp1").physics("tcd").feature("init1").setIndex("initc", "cNi0", 0);
    model.component("comp1").physics("tcd").feature("init1").setIndex("initc", "cH3PO20", 1);
    model.component("comp1").physics("tcd").feature("init1").setIndex("initc", "cSO40", 2);
    model.component("comp1").physics("tcd").feature("init1").setIndex("initc", "cNa0", 3);
    model.component("comp1").physics("tcd").create("es1", "ElectrodeSurface", 0);
    model.component("comp1").physics("tcd").feature("es1").selection().set(1);
    model.component("comp1").physics("tcd").feature("es1").setIndex("Species", "s1", 0, 0);
    model.component("comp1").physics("tcd").feature("es1").setIndex("rhos", 8960, 0, 0);
    model.component("comp1").physics("tcd").feature("es1").setIndex("Ms", 0.06355, 0, 0);
    model.component("comp1").physics("tcd").feature("es1").setIndex("Species", "s1", 0, 0);
    model.component("comp1").physics("tcd").feature("es1").setIndex("rhos", 8960, 0, 0);
    model.component("comp1").physics("tcd").feature("es1").setIndex("Ms", 0.06355, 0, 0);
    model.component("comp1").physics("tcd").feature("es1").setIndex("Species", "Ni", 0, 0);
    model.component("comp1").physics("tcd").feature("es1").setIndex("rhos", "rho_Ni", 0, 0);
    model.component("comp1").physics("tcd").feature("es1").setIndex("Ms", "M_Ni", 0, 0);
    model.component("comp1").physics("tcd").feature("es1").setIndex("Species", "s1", 1, 0);
    model.component("comp1").physics("tcd").feature("es1").setIndex("rhos", 8960, 1, 0);
    model.component("comp1").physics("tcd").feature("es1").setIndex("Ms", 0.06355, 1, 0);
    model.component("comp1").physics("tcd").feature("es1").setIndex("rhos", 8960, 1, 0);
    model.component("comp1").physics("tcd").feature("es1").setIndex("Ms", 0.06355, 1, 0);
    model.component("comp1").physics("tcd").feature("es1").setIndex("Species", "s1", 1, 0);
    model.component("comp1").physics("tcd").feature("es1").setIndex("rhos", 8960, 1, 0);
    model.component("comp1").physics("tcd").feature("es1").setIndex("Ms", 0.06355, 1, 0);
    model.component("comp1").physics("tcd").feature("es1").setIndex("Species", "P", 1, 0);
    model.component("comp1").physics("tcd").feature("es1").setIndex("rhos", "rho_P", 1, 0);
    model.component("comp1").physics("tcd").feature("es1").setIndex("Ms", "M_P", 1, 0);
    model.component("comp1").physics("tcd").feature("es1").set("phisext0", "E_app");
    model.component("comp1").physics("tcd").feature("es1").feature("er1")
         .label("\u7535\u6781\u53cd\u5e94\uff1aNi \u6c89\u79ef");
    model.component("comp1").physics("tcd").feature("es1").feature("er1").set("nm", 2);
    model.component("comp1").physics("tcd").feature("es1").feature("er1").setIndex("Vi0", -1, 0);
    model.component("comp1").physics("tcd").feature("es1").feature("er1").setIndex("Vib", 1, 0, 0);
    model.component("comp1").physics("tcd").feature("es1").feature("er1")
         .set("Eeq_ref", "Eeq_ref_Ni-R_const*T/(2*F_const)*log(max(xNi,eps^2)/aNi_ref)");
    model.component("comp1").physics("tcd").feature("es1").feature("er1").setIndex("cref", "cNi_ref", 0, 0);
    model.component("comp1").physics("tcd").feature("es1").feature("er1")
         .set("i0_ref", "i0_ref_Ni*(max(xNi,eps)/aNi_ref)^0.75");
    model.component("comp1").physics("tcd").feature("es1").create("er2", "ElectrodeReaction", 0);
    model.component("comp1").physics("tcd").feature("es1").feature("er2")
         .label("\u7535\u6781\u53cd\u5e94\uff1aP \u6c89\u79ef");
    model.component("comp1").physics("tcd").feature("es1").feature("er2").setIndex("Vi0", -1, 1);
    model.component("comp1").physics("tcd").feature("es1").feature("er2").setIndex("Vib", 1, 1, 0);
    model.component("comp1").physics("tcd").feature("es1").feature("er2")
         .set("Eeq_ref", "Eeq_ref_P-R_const*T/(F_const)*log(max(xP,eps)/aP_ref)");
    model.component("comp1").physics("tcd").feature("es1").feature("er2").setIndex("cref", "cH3PO2_ref", 1, 0);
    model.component("comp1").physics("tcd").feature("es1").feature("er2").set("cHref", "cH_ref");
    model.component("comp1").physics("tcd").feature("es1").feature("er2")
         .set("i0_ref", "i0_ref_P*(max(xP,eps^2)/aP_ref)^0.5");
    model.component("comp1").physics("tcd").feature("es1").create("er3", "ElectrodeReaction", 0);
    model.component("comp1").physics("tcd").feature("es1").feature("er3")
         .label("\u7535\u6781\u53cd\u5e94\uff1a\u6790\u6c22");
    model.component("comp1").physics("tcd").feature("es1").feature("er3").set("Eeq_ref", "Eeq_ref_H");
    model.component("comp1").physics("tcd").feature("es1").feature("er3").set("cHref", "cH_ref");
    model.component("comp1").physics("tcd").feature("es1").feature("er3").set("i0_ref", "i0_ref_H");
    model.component("comp1").physics("tcd").create("conc1", "Concentration", 0);
    model.component("comp1").physics("tcd").feature("conc1").selection().set(2);
    model.component("comp1").physics("tcd").feature("conc1").setIndex("species", true, 0);
    model.component("comp1").physics("tcd").feature("conc1").setIndex("species", true, 1);
    model.component("comp1").physics("tcd").feature("conc1").setIndex("species", true, 2);
    model.component("comp1").physics("tcd").feature("conc1").setIndex("species", true, 3);
    model.component("comp1").physics("tcd").feature("conc1").setIndex("c0", "cNi0", 0);
    model.component("comp1").physics("tcd").feature("conc1").setIndex("c0", "cH3PO20", 1);
    model.component("comp1").physics("tcd").feature("conc1").setIndex("c0", "cSO40", 2);
    model.component("comp1").physics("tcd").feature("conc1").setIndex("c0", "cNa0", 3);
    model.component("comp1").physics("tcd").create("eip1", "ElectrolytePotential", 0);
    model.component("comp1").physics("tcd").feature("eip1").selection().set(2);
    model.component("comp1").physics().create("gb", "GeneralFormBoundaryPDE", "geom1");

    model.study("std1").feature("stat").setSolveFor("/physics/gb", true);

    model.component("comp1").physics("gb").prop("EquationForm").set("form", "Automatic");
    model.component("comp1").physics("gb").field("dimensionless").component(new String[]{"xNi"});
    model.component("comp1").physics("gb").feature("gfeq1").set("Ga", new String[][]{{"-xNiTx"}});
    model.component("comp1").physics("gb").selection().set(1);
    model.component("comp1").physics("gb").prop("Units").setIndex("CustomSourceTermUnit", 1, 0, 0);
    model.component("comp1").physics("gb").feature("gfeq1").setIndex("f", "xNi-xNi_expr", 0);
    model.component("comp1").physics("gb").feature("gfeq1").setIndex("da", 0, 0);
    model.component("comp1").physics("gb").feature("init1").set("xNi", 1);

    model.common("cminpt").set("modified", new String[][]{{"temperature", "T"}});

    model.component("comp1").mesh("mesh1").automatic(false);
    model.component("comp1").mesh("mesh1").feature("size").set("hauto", 2);
    model.component("comp1").mesh("mesh1").feature("size1").set("custom", true);
    model.component("comp1").mesh("mesh1").feature("size1").set("hmaxactive", true);
    model.component("comp1").mesh("mesh1").feature("size1").set("hmax", 1.0E-6);
    model.component("comp1").mesh("mesh1").run();

    model.study("std1").feature("stat").set("useparam", true);
    model.study("std1").feature("stat").setIndex("pname", "a", 0);
    model.study("std1").feature("stat").setIndex("plistarr", "", 0);
    model.study("std1").feature("stat").setIndex("punit", "", 0);
    model.study("std1").feature("stat").setIndex("pname", "a", 0);
    model.study("std1").feature("stat").setIndex("plistarr", "", 0);
    model.study("std1").feature("stat").setIndex("punit", "", 0);
    model.study("std1").feature("stat").setIndex("pname", "E_app", 0);
    model.study("std1").feature("stat").setIndex("plistarr", "range(-0.5, -0.025, -1.1)", 0);
    model.study("std1").createAutoSequences("all");

    model.sol("sol1").runAll();

    model.result().create("pg1", "PlotGroup1D");
    model.result("pg1").set("data", "dset1");
    model.result("pg1").label("\u7535\u89e3\u8d28\u7535\u4f4d (tcd)");
    model.result("pg1").create("lngr1", "LineGraph");
    model.result("pg1").feature("lngr1").set("xdata", "expr");
    model.result("pg1").feature("lngr1").set("xdataexpr", "x");
    model.result("pg1").feature("lngr1").selection().geom("geom1", 1);
    model.result("pg1").feature("lngr1").selection().set(1);
    model.result("pg1").feature("lngr1").set("expr", new String[]{"phil"});
    model.result().create("pg2", "PlotGroup1D");
    model.result("pg2").set("data", "dset1");
    model.result("pg2").set("titletype", "manual");
    model.result("pg2").set("title", "\u6d53\u5ea6\uff0c\u6240\u6709\u7269\u8d28");
    model.result("pg2").label("\u6d53\u5ea6\uff0c\u6240\u6709\u7269\u8d28 (tcd)");
    model.result("pg2").create("lngr1", "LineGraph");
    model.result("pg2").feature("lngr1").set("xdata", "expr");
    model.result("pg2").feature("lngr1").set("xdataexpr", "x");
    model.result("pg2").feature("lngr1").selection().geom("geom1", 1);
    model.result("pg2").feature("lngr1").selection().set(1);
    model.result("pg2").feature("lngr1").set("expr", new String[]{"cNi"});
    model.result("pg2").feature("lngr1").label("\u7269\u8d28 Ni");
    model.result("pg2").feature("lngr1").set("legend", true);
    model.result("pg2").feature("lngr1").set("autosolution", false);
    model.result("pg2").feature("lngr1").set("autoexpr", false);
    model.result("pg2").feature("lngr1").set("autodescr", false);
    model.result("pg2").feature("lngr1").set("legendprefix", "Ni ");
    model.result("pg2").feature("lngr1").set("descractive", true);
    model.result("pg2").feature("lngr1").set("descr", "\u6d53\u5ea6");
    model.result("pg2").create("lngr2", "LineGraph");
    model.result("pg2").feature("lngr2").set("xdata", "expr");
    model.result("pg2").feature("lngr2").set("xdataexpr", "x");
    model.result("pg2").feature("lngr2").selection().geom("geom1", 1);
    model.result("pg2").feature("lngr2").selection().set(1);
    model.result("pg2").feature("lngr2").set("expr", new String[]{"cH3PO2"});
    model.result("pg2").feature("lngr2").label("\u7269\u8d28 H3PO2");
    model.result("pg2").feature("lngr2").set("legend", true);
    model.result("pg2").feature("lngr2").set("autosolution", false);
    model.result("pg2").feature("lngr2").set("autoexpr", false);
    model.result("pg2").feature("lngr2").set("autodescr", false);
    model.result("pg2").feature("lngr2").set("legendprefix", "H3PO2 ");
    model.result("pg2").feature("lngr2").set("descractive", true);
    model.result("pg2").feature("lngr2").set("descr", "\u6d53\u5ea6");
    model.result("pg2").create("lngr3", "LineGraph");
    model.result("pg2").feature("lngr3").set("xdata", "expr");
    model.result("pg2").feature("lngr3").set("xdataexpr", "x");
    model.result("pg2").feature("lngr3").selection().geom("geom1", 1);
    model.result("pg2").feature("lngr3").selection().set(1);
    model.result("pg2").feature("lngr3").set("expr", new String[]{"cSO4"});
    model.result("pg2").feature("lngr3").label("\u7269\u8d28 SO4");
    model.result("pg2").feature("lngr3").set("legend", true);
    model.result("pg2").feature("lngr3").set("autosolution", false);
    model.result("pg2").feature("lngr3").set("autoexpr", false);
    model.result("pg2").feature("lngr3").set("autodescr", false);
    model.result("pg2").feature("lngr3").set("legendprefix", "SO4 ");
    model.result("pg2").feature("lngr3").set("descractive", true);
    model.result("pg2").feature("lngr3").set("descr", "\u6d53\u5ea6");
    model.result("pg2").create("lngr4", "LineGraph");
    model.result("pg2").feature("lngr4").set("xdata", "expr");
    model.result("pg2").feature("lngr4").set("xdataexpr", "x");
    model.result("pg2").feature("lngr4").selection().geom("geom1", 1);
    model.result("pg2").feature("lngr4").selection().set(1);
    model.result("pg2").feature("lngr4").set("expr", new String[]{"cNa"});
    model.result("pg2").feature("lngr4").label("\u7269\u8d28 Na");
    model.result("pg2").feature("lngr4").set("legend", true);
    model.result("pg2").feature("lngr4").set("autosolution", false);
    model.result("pg2").feature("lngr4").set("autoexpr", false);
    model.result("pg2").feature("lngr4").set("autodescr", false);
    model.result("pg2").feature("lngr4").set("legendprefix", "Na ");
    model.result("pg2").feature("lngr4").set("descractive", true);
    model.result("pg2").feature("lngr4").set("descr", "\u6d53\u5ea6");
    model.result().create("pg3", "PlotGroup1D");
    model.result("pg3").set("data", "dset1");
    model.result("pg3").label("\u6d53\u5ea6, Ni (tcd)");
    model.result("pg3").set("titletype", "custom");
    model.result("pg3").set("prefixintitle", "");
    model.result("pg3").set("expressionintitle", false);
    model.result("pg3").set("typeintitle", false);
    model.result("pg3").create("lngr1", "LineGraph");
    model.result("pg3").feature("lngr1").set("xdata", "expr");
    model.result("pg3").feature("lngr1").set("xdataexpr", "x");
    model.result("pg3").feature("lngr1").selection().geom("geom1", 1);
    model.result("pg3").feature("lngr1").selection().set(1);
    model.result("pg3").feature("lngr1").set("expr", new String[]{"cNi"});
    model.result().create("pg4", "PlotGroup1D");
    model.result("pg4").set("data", "dset1");
    model.result("pg4").label("\u6d53\u5ea6, H3PO2 (tcd)");
    model.result("pg4").set("titletype", "custom");
    model.result("pg4").set("prefixintitle", "");
    model.result("pg4").set("expressionintitle", false);
    model.result("pg4").set("typeintitle", false);
    model.result("pg4").create("lngr1", "LineGraph");
    model.result("pg4").feature("lngr1").set("xdata", "expr");
    model.result("pg4").feature("lngr1").set("xdataexpr", "x");
    model.result("pg4").feature("lngr1").selection().geom("geom1", 1);
    model.result("pg4").feature("lngr1").selection().set(1);
    model.result("pg4").feature("lngr1").set("expr", new String[]{"cH3PO2"});
    model.result().create("pg5", "PlotGroup1D");
    model.result("pg5").set("data", "dset1");
    model.result("pg5").label("\u6d53\u5ea6, SO4 (tcd)");
    model.result("pg5").set("titletype", "custom");
    model.result("pg5").set("prefixintitle", "");
    model.result("pg5").set("expressionintitle", false);
    model.result("pg5").set("typeintitle", false);
    model.result("pg5").create("lngr1", "LineGraph");
    model.result("pg5").feature("lngr1").set("xdata", "expr");
    model.result("pg5").feature("lngr1").set("xdataexpr", "x");
    model.result("pg5").feature("lngr1").selection().geom("geom1", 1);
    model.result("pg5").feature("lngr1").selection().set(1);
    model.result("pg5").feature("lngr1").set("expr", new String[]{"cSO4"});
    model.result().create("pg6", "PlotGroup1D");
    model.result("pg6").set("data", "dset1");
    model.result("pg6").label("\u6d53\u5ea6, Na (tcd)");
    model.result("pg6").set("titletype", "custom");
    model.result("pg6").set("prefixintitle", "");
    model.result("pg6").set("expressionintitle", false);
    model.result("pg6").set("typeintitle", false);
    model.result("pg6").create("lngr1", "LineGraph");
    model.result("pg6").feature("lngr1").set("xdata", "expr");
    model.result("pg6").feature("lngr1").set("xdataexpr", "x");
    model.result("pg6").feature("lngr1").selection().geom("geom1", 1);
    model.result("pg6").feature("lngr1").selection().set(1);
    model.result("pg6").feature("lngr1").set("expr", new String[]{"cNa"});
    model.result("pg1").run();
    model.result("pg2").run();
    model.result("pg2").setIndex("looplevelinput", "manual", 0);
    model.result("pg2").setIndex("looplevel", new int[]{13}, 0);
    model.result("pg2")
         .set("title", "\u5728 -0.8 V/SHE \u7684\u5916\u52a0\u7535\u4f4d\u4e0b\uff0c\u6240\u6709\u7269\u8d28\u7684\u6d53\u5ea6\u3002");
    model.result("pg2").set("xlabelactive", true);
    model.result("pg2").set("xlabel", "\u5f52\u4e00\u5316\u8ddd\u79bb");
    model.result("pg2").set("ylabelactive", true);
    model.result("pg2").set("axislimits", true);
    model.result("pg2").set("xmin", 0);
    model.result("pg2").set("xmax", 2);
    model.result("pg2").set("legendpos", "upperleft");
    model.result("pg2").run();
    model.result("pg2").feature("lngr1").set("xdataexpr", "x/1.1517e-3[cm]");
    model.result("pg2").feature("lngr1").set("linewidth", 2);
    model.result("pg2").run();
    model.result("pg2").feature("lngr2").set("xdataexpr", "x/1.1517e-3[cm]");
    model.result("pg2").feature("lngr2").set("linewidth", 2);
    model.result("pg2").run();
    model.result("pg2").feature("lngr3").set("xdataexpr", "x/1.1517e-3[cm]");
    model.result("pg2").feature("lngr3").set("linewidth", 2);
    model.result("pg2").run();
    model.result("pg2").feature("lngr4").set("xdataexpr", "x/1.1517e-3[cm]");
    model.result("pg2").feature("lngr4").set("linewidth", 2);
    model.result("pg2").feature().duplicate("lngr5", "lngr4");
    model.result("pg2").run();
    model.result("pg2").feature("lngr5").label("\u7269\u8d28 H");
    model.result("pg2").feature("lngr5").set("expr", "tcd.cH");
    model.result("pg2").feature("lngr5").set("legendprefix", "H");
    model.result("pg2").run();
    model.result().create("pg7", "PlotGroup1D");
    model.result("pg7").run();
    model.result("pg7").label("\u6781\u5316\u56fe");
    model.result("pg7").set("titletype", "manual");
    model.result("pg7").set("title", "\u6781\u5316\u56fe");
    model.result("pg7").create("ptgr1", "PointGraph");
    model.result("pg7").feature("ptgr1").set("markerpos", "datapoints");
    model.result("pg7").feature("ptgr1").set("linewidth", "preference");
    model.result("pg7").feature("ptgr1").selection().set(1);
    model.result("pg7").feature("ptgr1").set("expr", "tcd.itot");
    model.result("pg7").feature("ptgr1").set("descr", "\u754c\u9762\u603b\u7535\u6d41\u5bc6\u5ea6");
    model.result("pg7").feature("ptgr1").set("unit", "A/cm^2");
    model.result("pg7").feature("ptgr1").set("xdata", "expr");
    model.result("pg7").feature("ptgr1").set("xdataexpr", "E_app");
    model.result("pg7").feature("ptgr1").set("linewidth", 2);
    model.result("pg7").run();
    model.result().create("pg8", "PlotGroup1D");
    model.result("pg8").run();
    model.result("pg8").label("\u6c89\u79ef\u6469\u5c14\u5206\u6570");
    model.result("pg8").set("titletype", "manual");
    model.result("pg8").set("title", "\u6c89\u79ef\u6469\u5c14\u5206\u6570");
    model.result("pg8").set("xlabelactive", true);
    model.result("pg8").set("xlabel", "\u5916\u52a0\u7535\u4f4d (V)");
    model.result("pg8").set("ylabelactive", true);
    model.result("pg8").set("ylabel", "\u6c89\u79ef\u6469\u5c14\u5206\u6570 (1)");
    model.result("pg8").set("legendpos", "middleleft");
    model.result("pg8").create("ptgr1", "PointGraph");
    model.result("pg8").feature("ptgr1").set("markerpos", "datapoints");
    model.result("pg8").feature("ptgr1").set("linewidth", "preference");
    model.result("pg8").feature("ptgr1").selection().set(1);
    model.result("pg8").feature("ptgr1").set("expr", "xNi");
    model.result("pg8").feature("ptgr1").set("xdata", "expr");
    model.result("pg8").feature("ptgr1").set("xdataexpr", "E_app");
    model.result("pg8").feature("ptgr1").set("linewidth", 2);
    model.result("pg8").feature("ptgr1").set("legend", true);
    model.result("pg8").feature("ptgr1").set("legendmethod", "manual");
    model.result("pg8").feature("ptgr1").setIndex("legends", "Ni", 0);
    model.result("pg8").feature().duplicate("ptgr2", "ptgr1");
    model.result("pg8").run();
    model.result("pg8").feature("ptgr2").set("expr", "xP");
    model.result("pg8").feature("ptgr2").setIndex("legends", "P", 0);
    model.result("pg8").run();

    model.title("\u5408\u91d1\u6c89\u79ef");

    model
         .description("\u672c\u6559\u5b66\u6a21\u578b\u6f14\u793a\u954d-\u78f7\u5408\u91d1\u7684\u7535\u6c89\u79ef\u8fc7\u7a0b\uff0c\u5176\u4e2d\u8003\u8651\u4e86\u591a\u79cd\u7269\u8d28\u7684\u7535\u8377\u548c\u8d28\u91cf\u4f20\u9012\uff0c\u4ee5\u53ca\u591a\u79cd\u7535\u6781\u53cd\u5e94\uff0c\u4f8b\u5982\u954d\u548c\u78f7\u7684\u7535\u6c89\u79ef\u548c\u6790\u6c22\u53cd\u5e94\u3002\u901a\u8fc7\u8ba1\u7b97\u5404\u79cd\u7269\u8d28\u6cbf\u6269\u6563\u5c42\u7684\u7a33\u6001\u7a7a\u95f4\u5206\u5e03\uff0c\u4f7f\u7528\u6781\u5316\u56fe\u548c\u6c89\u79ef\u6469\u5c14\u5206\u6570\u56fe\u63ed\u793a\u4e86\u5408\u91d1\u5236\u5907\u7684\u7406\u60f3\u5de5\u4f5c\u6761\u4ef6\u3002");

    model.mesh().clearMeshes();

    model.sol("sol1").clearSolutionData();

    model.label("alloy_deposition.mph");

    return model;
  }

  public static void main(String[] args) {
    run();
  }

}
