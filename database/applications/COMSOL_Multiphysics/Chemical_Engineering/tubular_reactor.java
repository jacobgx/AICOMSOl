/*
 * tubular_reactor.java
 */

import com.comsol.model.*;
import com.comsol.model.util.*;

/** Model exported on Apr 29 2026, 18:33 by COMSOL 6.3.0.290. */
public class tubular_reactor {

  public static Model run() {
    Model model = ModelUtil.create("Model");

    model
         .modelPath("D:\\Program Files\\COMSOL\\COMSOL63\\Multiphysics\\applications\\COMSOL_Multiphysics\\Chemical_Engineering");

    model.component().create("comp1", true);

    model.component("comp1").geom().create("geom1", 2);
    model.component("comp1").geom("geom1").axisymmetric(true);

    model.component("comp1").mesh().create("mesh1");

    model.component("comp1").physics().create("tds", "DilutedSpecies", "geom1");
    model.component("comp1").physics("tds").field("concentration").field("cA");
    model.component("comp1").physics("tds").field("concentration").component(new String[]{"cA"});
    model.component("comp1").physics().create("ht", "HeatTransferInFluids", "geom1");
    model.component("comp1").physics().create("cb", "CoefficientFormBoundaryPDE", "geom1");
    model.component("comp1").physics("cb").prop("EquationForm").set("form", "Automatic");
    model.component("comp1").physics("cb").prop("Units").set("DependentVariableQuantity", "temperature");
    model.component("comp1").physics("cb").field("dimensionless").component(new String[]{"Tj"});
    model.component("comp1").physics("cb").prop("Units").set("CustomSourceTermUnit", "W/m");

    model.study().create("std1");
    model.study("std1").create("stat", "Stationary");
    model.study("std1").feature("stat").setSolveFor("/physics/tds", true);
    model.study("std1").feature("stat").setSolveFor("/physics/ht", true);
    model.study("std1").feature("stat").setSolveFor("/physics/cb", true);

    model.component("comp1").geom("geom1").create("r1", "Rectangle");
    model.component("comp1").geom("geom1").feature("r1").set("size", new double[]{0.1, 1});
    model.component("comp1").geom("geom1").runPre("fin");

//    To import content from file, use:
//    model.param().loadFile("FILENAME");
    model.param().set("E", "75362[J/mol]", "\u6d3b\u5316\u80fd");
    model.param().set("A", "16.96e12[1/h]", "\u9891\u7387\u56e0\u5b50");
    model.param().set("ke", "0.559[W/m/K]", "\u5bfc\u70ed\u7cfb\u6570");
    model.param().set("Diff", "1e-9[m^2/s]", "\u6269\u6563\u7cfb\u6570");
    model.param().set("Uk", "1300[W/m^2/K]", "\u603b\u4f20\u70ed\u7cfb\u6570");
    model.param().set("dHrx", "-84666[J/mol]", "\u53cd\u5e94\u70ed");
    model.param().set("T0", "312[K]", "\u5165\u53e3\u6e29\u5ea6");
    model.param().set("Ta0", "277[K]", "\u51b7\u5374\u6db2\u5165\u53e3\u6e29\u5ea6");
    model.param().set("v0", "v_w0+v_po0+v_m0", "\u603b\u6d41\u7387");
    model.param().set("cA0", "n_po0/v0", "\u73af\u6c27\u4e19\u70f7\u6d53\u5ea6\uff0c\u5165\u53e3");
    model.param().set("cB0", "n_w0/v0", "\u6c34\u6d53\u5ea6\uff0c\u5165\u53e3");
    model.param().set("cMe0", "n_m0/v0", "\u7532\u9187\u6d53\u5ea6\uff0c\u5165\u53e3");
    model.param().set("Cp0", "(Cp_po*cA0+Cp_m*cMe0+Cp_w*cB0)/rho0", "\u5165\u53e3\u7684\u70ed\u5bb9");
    model.param().set("rho0", "(cA0*M_po+cB0*M_w+cMe0*M_m)", "\u5165\u53e3\u7684\u5bc6\u5ea6");
    model.param().set("Ra", "0.1[m]", "\u53cd\u5e94\u5668\u534a\u5f84");
    model.param().set("L", "1[m]", "\u53cd\u5e94\u5668\u957f\u5ea6");
    model.param().set("M_po", "58.095[g/mol]", "\u6469\u5c14\u8d28\u91cf\uff0c\u73af\u6c27\u4e19\u70f7");
    model.param().set("M_m", "32.042[g/mol]", "\u6469\u5c14\u8d28\u91cf\uff0c\u7532\u9187");
    model.param().set("M_w", "18[g/mol]", "\u6469\u5c14\u8d28\u91cf\uff0c\u6c34");
    model.param().set("rho_po_p", "830[kg/m^3]", "\u5bc6\u5ea6\uff0c\u73af\u6c27\u4e19\u70f7");
    model.param().set("rho_m_p", "791.3[kg/m^3]", "\u5bc6\u5ea6\uff0c\u7532\u9187");
    model.param().set("rho_w_p", "1000[kg/m^3]", "\u5bc6\u5ea6\uff0c\u6c34");
    model.param().set("Cp_po", "146.54[J/mol/K]", "\u6bd4\u70ed\uff0cpo");
    model.param().set("Cp_m", "81.095[J/mol/K]", "\u6bd4\u70ed\uff0cm");
    model.param().set("Cp_w", "75.36[J/mol/K]", "\u6bd4\u70ed\uff0cw");
    model.param().set("Cp_pg", "192.59[J/mol/K]", "\u6bd4\u70ed\uff0cpg");
    model.param()
         .set("v_ratio", "3.5", "\u6c34\u6d41\u91cf/\uff08\u73af\u6c27\u4e19\u70f7+\u7532\u9187\uff09\u6d41\u7387\u4e4b\u6bd4");
    model.param().set("n_po0", "0.1[mol/s]", "\u5165\u53e3\u73af\u6c27\u4e19\u70f7\u6469\u5c14\u6d41\u7387");
    model.param().set("n_m0", "v_po0*rho_m_p/M_m", "\u5165\u53e3\u7532\u9187\u6469\u5c14\u6d41\u7387");
    model.param()
         .set("v_po0", "n_po0*M_po/rho_po_p", "\u5165\u53e3\u73af\u6c27\u4e19\u70f7\u4f53\u79ef\u6d41\u7387");
    model.param().set("v_m0", "v_po0", "\u5165\u53e3\u7532\u9187\u4f53\u79ef\u6d41\u7387");
    model.param().set("v_w0", "v_ratio*(v_po0+v_m0)", "\u5165\u53e3\u6c34\u4f53\u79ef\u6d41\u7387");
    model.param().set("n_w0", "rho_w_p*v_w0/M_w", "\u5165\u53e3\u6c34\u6469\u5c14\u6d41\u7387");
    model.param().set("Cpc", "4180[J/(kg*K)]", "\u70ed\u5bb9\uff0c\u51b7\u5374\u6d41\u4f53");
    model.param().set("mc", "0.1[kg/s]", "\u8d28\u91cf\u6d41\u7387\uff0c\u51b7\u5374\u6d41\u4f53");

    model.component("comp1").variable().create("var1");

    model.component("comp1").geom("geom1").run();

//    To import content from file, use:
//    model.component("comp1").variable("var1").loadFile("FILENAME");
    model.component("comp1").variable("var1").set("u0", "v0/(pi*Ra^2)", "\u5e73\u5747\u6d41\u7387");
    model.component("comp1").variable("var1").set("uz", "2*u0*(1-(r/Ra)^2)", "\u5c42\u6d41\u901f\u5ea6\u5206\u5e03");
    model.component("comp1").variable("var1").set("xA", "(cA0-cA)/cA0", "\u8f6c\u5316\u7387\uff0c\u7269\u8d28 A");
    model.component("comp1").variable("var1").set("cB", "cB0-cA0*xA", "\u6d53\u5ea6\uff0c\u7269\u8d28 B");
    model.component("comp1").variable("var1").set("cC", "cA0*xA", "\u6d53\u5ea6\uff0c\u7269\u8d28 C");
    model.component("comp1").variable("var1").set("rA", "-A*exp(-E/R_const/T)*cA", "\u53cd\u5e94\u901f\u7387");
    model.component("comp1").variable("var1").set("Q", "(-rA)*(-dHrx)", "\u4ea7\u70ed\u9879");
    model.component("comp1").variable("var1")
         .set("cpm", "(Cp_po*cA+Cp_m*cMe0+Cp_w*cB)/rho0", "\u6df7\u5408\u7269\u6bd4\u70ed");

    model.component("comp1").physics("tds").feature("cdm1").set("u", new String[]{"0", "0", "uz"});
    model.component("comp1").physics("tds").feature("cdm1")
         .set("D_cA", new String[]{"Diff", "0", "0", "0", "Diff", "0", "0", "0", "Diff"});
    model.component("comp1").physics("tds").feature("init1").setIndex("initc", "cA0", 0);
    model.component("comp1").physics("tds").create("reac1", "Reactions", 2);
    model.component("comp1").physics("tds").feature("reac1").selection().set(1);
    model.component("comp1").physics("tds").feature("reac1").setIndex("R_cA", "rA", 0);
    model.component("comp1").physics("tds").create("conc1", "Concentration", 1);
    model.component("comp1").physics("tds").feature("conc1").selection().set(2);
    model.component("comp1").physics("tds").feature("conc1").setIndex("species", true, 0);
    model.component("comp1").physics("tds").feature("conc1").setIndex("c0", "cA0", 0);
    model.component("comp1").physics("tds").create("out1", "Outflow", 1);
    model.component("comp1").physics("tds").feature("out1").selection().set(3);
    model.component("comp1").physics("ht").feature("fluid1").set("u", new String[]{"0", "0", "uz"});
    model.component("comp1").physics("ht").feature("fluid1").set("k_mat", "userdef");
    model.component("comp1").physics("ht").feature("fluid1")
         .set("k", new String[]{"ke", "0", "0", "0", "ke", "0", "0", "0", "ke"});
    model.component("comp1").physics("ht").feature("fluid1").set("fluidType", "gasLiquid");
    model.component("comp1").physics("ht").feature("fluid1").set("rho_mat", "userdef");
    model.component("comp1").physics("ht").feature("fluid1").set("rho", "rho0");
    model.component("comp1").physics("ht").feature("fluid1").set("Cp_mat", "userdef");
    model.component("comp1").physics("ht").feature("fluid1").set("Cp", "cpm");
    model.component("comp1").physics("ht").feature("fluid1").set("gamma_not_IG_mat", "userdef");
    model.component("comp1").physics("ht").feature("init1").set("Tinit", "T0");
    model.component("comp1").physics("ht").create("hs1", "HeatSource", 2);
    model.component("comp1").physics("ht").feature("hs1").selection().set(1);
    model.component("comp1").physics("ht").feature("hs1").set("Q0", "Q");
    model.component("comp1").physics("ht").create("temp1", "TemperatureBoundary", 1);
    model.component("comp1").physics("ht").feature("temp1").selection().set(2);
    model.component("comp1").physics("ht").feature("temp1").set("T0", "T0");
    model.component("comp1").physics("ht").create("hf1", "HeatFluxBoundary", 1);
    model.component("comp1").physics("ht").feature("hf1").selection().set(4);
    model.component("comp1").physics("ht").feature("hf1").set("q0_input", "-Uk*(T-Tj)");
    model.component("comp1").physics("ht").create("ofl1", "ConvectiveOutflow", 1);
    model.component("comp1").physics("ht").feature("ofl1").selection().set(3);
    model.component("comp1").physics("cb").selection().set(4);
    model.component("comp1").physics("cb").feature("cfeq1").setIndex("c", new int[]{0, 0, 0, 0}, 0);
    model.component("comp1").physics("cb").feature("cfeq1").setIndex("f", "2*pi*Ra*Uk*(T-Tj)", 0);
    model.component("comp1").physics("cb").feature("cfeq1").setIndex("be", new String[]{"0", "Cpc*mc"}, 0);
    model.component("comp1").physics("cb").create("dir1", "DirichletBoundary", 0);
    model.component("comp1").physics("cb").feature("dir1").selection().set(3);
    model.component("comp1").physics("cb").feature("dir1").setIndex("r", "Ta0", 0);

    model.component("comp1").mesh("mesh1").create("map1", "Map");
    model.component("comp1").mesh("mesh1").feature("map1").create("dis1", "Distribution");
    model.component("comp1").mesh("mesh1").feature("map1").feature("dis1").selection().set(2, 3);
    model.component("comp1").mesh("mesh1").feature("map1").feature("dis1").set("type", "predefined");
    model.component("comp1").mesh("mesh1").feature("map1").feature("dis1").set("elemcount", 50);
    model.component("comp1").mesh("mesh1").feature("map1").feature("dis1").set("elemratio", 0.01);
    model.component("comp1").mesh("mesh1").feature("map1").feature("dis1").set("growthrate", "exponential");
    model.component("comp1").mesh("mesh1").feature("map1").feature("dis1").set("reverse", true);
    model.component("comp1").mesh("mesh1").feature("map1").create("dis2", "Distribution");
    model.component("comp1").mesh("mesh1").feature("map1").feature("dis2").selection().set(1, 4);
    model.component("comp1").mesh("mesh1").feature("map1").feature("dis2").set("type", "predefined");
    model.component("comp1").mesh("mesh1").feature("map1").feature("dis2").set("elemcount", 200);
    model.component("comp1").mesh("mesh1").feature("map1").feature("dis2").set("elemratio", 0.01);
    model.component("comp1").mesh("mesh1").feature("map1").feature("dis2").set("growthrate", "exponential");
    model.component("comp1").mesh("mesh1").feature("map1").feature("dis2").set("reverse", true);
    model.component("comp1").mesh("mesh1").run();

    model.study("std1").create("stat2", "Stationary");
    model.study("std1").feature("stat").setSolveFor("/physics/ht", false);
    model.study("std1").createAutoSequences("all");

    model.sol("sol1").runAll();

    model.result().create("pg1", "PlotGroup2D");
    model.result("pg1").set("data", "dset1");
    model.result("pg1").label("\u6d53\u5ea6 (tds)");
    model.result("pg1").set("titletype", "custom");
    model.result("pg1").set("prefixintitle", "");
    model.result("pg1").set("expressionintitle", false);
    model.result("pg1").set("typeintitle", false);
    model.result("pg1").create("surf1", "Surface");
    model.result("pg1").feature("surf1").set("expr", new String[]{"cA"});
    model.result("pg1").feature("surf1").set("colortable", "Prism");
    model.result("pg1").set("typeintitle", true);
    model.result("pg1").create("arws1", "ArrowSurface");
    model.result("pg1").feature("arws1").set("expr", new String[]{"tds.tflux_cAr", "tds.tflux_cAz"});
    model.result("pg1").feature("arws1").set("xnumber", 10);
    model.result("pg1").feature("arws1").set("ynumber", 10);
    model.result("pg1").feature("arws1").set("color", "black");
    model.result("pg1").feature("arws1").create("sel1", "Selection");
    model.result("pg1").feature("arws1").feature("sel1").selection().set(1);
    model.result().dataset().create("rev1", "Revolve2D");
    model.result().dataset("rev1").set("data", "dset1");
    model.result().dataset("rev1").set("revangle", 225);
    model.result().dataset("rev1").set("startangle", -90);
    model.result().dataset("rev1").set("hasspacevars", false);
    model.result().create("pg2", "PlotGroup3D");
    model.result("pg2").set("data", "rev1");
    model.result("pg2").label("\u6d53\u5ea6, 3D (tds)");
    model.result("pg2").create("surf1", "Surface");
    model.result("pg2").feature("surf1").set("expr", new String[]{"cA"});
    model.result("pg2").feature("surf1").set("colortable", "Prism");
    model.result("pg2").set("titletype", "custom");
    model.result("pg2").set("typeintitle", false);
    model.result("pg2").set("prefixintitle", "");
    model.result("pg2").set("expressionintitle", false);
    model.result().create("pg3", "PlotGroup2D");
    model.result("pg3").label("\u6e29\u5ea6 (ht)");
    model.result("pg3").set("dataisaxisym", "off");
    model.result("pg3").feature().create("surf1", "Surface");
    model.result("pg3").feature("surf1").set("showsolutionparams", "on");
    model.result("pg3").feature("surf1").set("solutionparams", "parent");
    model.result("pg3").feature("surf1").set("expr", "T");
    model.result("pg3").feature("surf1").set("colortable", "HeatCameraLight");
    model.result("pg3").feature("surf1").set("showsolutionparams", "on");
    model.result("pg3").feature("surf1").set("data", "parent");
    model.result().create("pg4", "PlotGroup2D");
    model.result("pg4").set("data", "dset1");
    model.result("pg4").create("line1", "Line");
    model.result("pg4").label("\u7cfb\u6570\u5f62\u5f0f\u8fb9\u754c\u504f\u5fae\u5206\u65b9\u7a0b");
    model.result("pg4").feature("line1").set("expr", "Tj");
    model.result("pg1").run();
    model.result().dataset().create("cln1", "CutLine2D");
    model.result().dataset("cln1").setIndex("genpoints", "Ra", 1, 0);
    model.result().dataset("cln1").set("genparaactive", true);
    model.result().dataset("cln1").set("genparadist", "0.5*L 1*L");
    model.result().dataset().create("mir1", "Mirror2D");
    model.result().create("pg5", "PlotGroup2D");
    model.result("pg5").run();
    model.result("pg5").label("\u6e29\u5ea6\uff08\u955c\u50cf\uff09");
    model.result("pg5").set("data", "mir1");
    model.result("pg5").set("titletype", "manual");
    model.result("pg5").set("title", "\u8868\u9762\u6e29\u5ea6");
    model.result("pg5").set("xlabelactive", true);
    model.result("pg5").set("xlabel", "\u5f84\u5411\u4f4d\u7f6e (m)");
    model.result("pg5").set("ylabelactive", true);
    model.result("pg5").set("ylabel", "\u8f74\u5411\u4f4d\u7f6e (m)");
    model.result("pg5").create("surf1", "Surface");
    model.result("pg5").feature("surf1").set("expr", "T");
    model.result("pg5").feature("surf1").set("descr", "\u6e29\u5ea6");
    model.result("pg5").run();
    model.result("pg5").run();
    model.result().duplicate("pg6", "pg5");
    model.result("pg6").run();
    model.result("pg6").label("\u8f6c\u5316\u7387");
    model.result("pg6").set("title", "\u8868\u9762\u8f6c\u5316\u7387");
    model.result("pg6").run();
    model.result("pg6").feature("surf1").set("expr", "xA");
    model.result("pg6").feature("surf1").set("descr", "\u8f6c\u5316\u7387\uff0c\u7269\u8d28 A");
    model.result("pg6").run();
    model.result().create("pg7", "PlotGroup1D");
    model.result("pg7").run();
    model.result("pg7").label("\u6e29\u5ea6\uff0c\u4e00\u7ef4");
    model.result("pg7").set("data", "cln1");
    model.result("pg7").set("titletype", "manual");
    model.result("pg7").set("title", "\u5f84\u5411\u6e29\u5ea6\u66f2\u7ebf");
    model.result("pg7").set("xlabelactive", true);
    model.result("pg7").set("xlabel", "\u5f84\u5411\u4f4d\u7f6e (m)");
    model.result("pg7").set("ylabelactive", true);
    model.result("pg7").set("ylabel", "\u6e29\u5ea6 (K)");
    model.result("pg7").create("lngr1", "LineGraph");
    model.result("pg7").feature("lngr1").set("markerpos", "datapoints");
    model.result("pg7").feature("lngr1").set("linewidth", "preference");
    model.result("pg7").feature("lngr1").set("expr", "T");
    model.result("pg7").feature("lngr1").set("descr", "\u6e29\u5ea6");
    model.result("pg7").feature("lngr1").set("linestyle", "cycle");
    model.result("pg7").feature("lngr1").set("linecolor", "black");
    model.result("pg7").feature("lngr1").set("legend", true);
    model.result("pg7").feature("lngr1").set("legendmethod", "manual");
    model.result("pg7").feature("lngr1").setIndex("legends", "\u5165\u53e3", 0);
    model.result("pg7").feature("lngr1").setIndex("legends", "\u534a\u8f74\u4f4d\u7f6e", 1);
    model.result("pg7").feature("lngr1").setIndex("legends", "\u51fa\u53e3", 2);
    model.result("pg7").run();
    model.result("pg7").set("legendpos", "lowerleft");
    model.result("pg7").run();
    model.result().duplicate("pg8", "pg7");
    model.result("pg8").run();
    model.result("pg8").label("\u4e00\u7ef4\u8f6c\u5316\u7387");
    model.result("pg8").set("ylabel", "\u8f6c\u5316\u7387");
    model.result("pg8").run();
    model.result("pg8").feature("lngr1").set("expr", "xA");
    model.result("pg8").feature("lngr1").set("descr", "\u8f6c\u5316\u7387\uff0c\u7269\u8d28 A");
    model.result("pg8").run();
    model.result("pg8").set("legendpos", "upperleft");
    model.result("pg8").run();
    model.result("pg4").run();
    model.result("pg4").label("\u51b7\u5374\u5957\u6e29\u5ea6");
    model.result("pg4").run();
    model.result("pg4").feature("line1").set("linetype", "tube");
    model.result("pg4").feature("line1").set("radiusexpr", "3");
    model.result("pg4").run();
    model.result("pg2").run();
    model.result("pg2").run();

    model.title("\u5e26\u975e\u7b49\u6e29\u51b7\u5374\u5939\u5957\u7684\u7ba1\u5f0f\u53cd\u5e94\u5668");

    model
         .description("\u672c\u4f8b\u63cf\u8ff0\u7ba1\u5f0f\u53cd\u5e94\u5668\u4e2d\u73af\u6c27\u4e19\u70f7 (A) \u4e0e\u6c34 (B) \u53d1\u751f\u53cd\u5e94\u4ea7\u751f\u4e19\u4e8c\u9187 (C)\uff1a\n\nA + B -> C\n\n\u8fd9\u91cc\u6c34\u662f\u6eb6\u5242\uff0c\u53cd\u5e94\u52a8\u529b\u5b66\u53ef\u4ee5\u63cf\u8ff0\u4e3a\u73af\u6c27\u4e19\u70f7\u7684\u4e00\u7ea7\u53cd\u5e94\n\nR = k*C_A\n\n\u53e6\u5916\uff0c\u6839\u636e\n\nR = kf*C_A*C_B - kr*C_C\n\n\u8fd8\u4f1a\u5b9e\u73b0\u4e00\u4e2a\u4e8c\u7ea7\u53cd\u5e94\uff0c\u6b64\u53cd\u5e94\u4e3a\u653e\u70ed\u53cd\u5e94\uff0c\u56e0\u6b64\u4f7f\u7528\u51b7\u5374\u5939\u5957\u4f7f\u53cd\u5e94\u5668\u964d\u6e29\u3002\u53cd\u5e94\u5668\u5728\u4e8c\u7ef4\u8f74\u5bf9\u79f0\u6a21\u5f0f\u4e0b\u5efa\u6a21\uff0c\u6839\u636e\u4eff\u771f\u7ed3\u679c\u53ef\u77e5\uff0c\u6210\u5206\u548c\u6e29\u5ea6\u5728\u5f84\u5411\u548c\u8f74\u5411\u90fd\u53d1\u751f\u4e86\u53d8\u5316\u3002");

    model.label("tubular_reactor.mph");

    return model;
  }

  public static void main(String[] args) {
    run();
  }

}
