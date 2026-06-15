/*
 * chloroprene_rubber_compression_test.java
 */

import com.comsol.model.*;
import com.comsol.model.util.*;

/** Model exported on Apr 30 2026, 11:38 by COMSOL 6.3.0.290. */
public class chloroprene_rubber_compression_test {

  public static Model run() {
    Model model = ModelUtil.create("Model");

    model
         .modelPath("D:\\Program Files\\COMSOL\\COMSOL63\\Multiphysics\\applications\\Nonlinear_Structural_Materials_Module\\Viscoplasticity");

    model.component().create("comp1", true);

    model.component("comp1").geom().create("geom1", 2);
    model.component("comp1").geom("geom1").axisymmetric(true);

    model.component("comp1").mesh().create("mesh1");

    model.component("comp1").physics().create("solid", "SolidMechanics", "geom1");

    model.study().create("std1");
    model.study("std1").create("time", "Transient");
    model.study("std1").feature("time").setSolveFor("/physics/solid", true);

    model.param().label("\u51e0\u4f55\u53c2\u6570");
    model.param().set("H", "13[mm]");
    model.param().descr("H", "\u8bd5\u6837\u9ad8\u5ea6");
    model.param().set("D", "28[mm]");
    model.param().descr("D", "\u8bd5\u6837\u76f4\u5f84");
    model.param().set("A0", "pi*D^2/4");
    model.param().descr("A0", "\u8bd5\u6837\u8868\u9762\u79ef");
    model.param().create("par2");
    model.param("par2").label("\u5e94\u53d8\u5386\u7a0b\u6570\u636e");
    model.param("par2").set("edot", "0.002[1/s]");
    model.param("par2").descr("edot", "\u771f\u5b9e\u5e94\u53d8\u7387");
    model.param("par2").set("Dt", "150[s]");
    model.param("par2").descr("Dt", "\u5f1b\u8c6b\u524d\u7684\u65f6\u95f4");
    model.param("par2").set("Rt", "120[s]");
    model.param("par2").descr("Rt", "\u5f1b\u8c6b\u65f6\u95f4");
    model.param("par2").set("endTime", "1280[s]");
    model.param("par2").descr("endTime", "\u603b\u4eff\u771f\u65f6\u95f4");

    model.component("comp1").geom("geom1").lengthUnit("mm");
    model.component("comp1").geom("geom1").create("r1", "Rectangle");
    model.component("comp1").geom("geom1").feature("r1").set("size", new String[]{"D/2", "H/2"});
    model.component("comp1").geom("geom1").run("r1");
    model.component("comp1").geom("geom1").runPre("fin");

    model.component("comp1").func().create("pw1", "Piecewise");
    model.component("comp1").func("pw1").set("arg", "time");
    model.component("comp1").func("pw1").setIndex("pieces", 0, 0, 0);
    model.component("comp1").func("pw1").setIndex("pieces", "Dt", 0, 1);
    model.component("comp1").func("pw1").setIndex("pieces", "edot*time", 0, 2);
    model.component("comp1").func("pw1").setIndex("pieces", "Dt", 1, 0);
    model.component("comp1").func("pw1").setIndex("pieces", "Dt+Rt", 1, 1);
    model.component("comp1").func("pw1").setIndex("pieces", "edot*Dt", 1, 2);
    model.component("comp1").func("pw1").setIndex("pieces", "Dt+Rt", 2, 0);
    model.component("comp1").func("pw1").setIndex("pieces", "2*Dt+Rt", 2, 1);
    model.component("comp1").func("pw1").setIndex("pieces", "edot*(time-Rt)", 2, 2);
    model.component("comp1").func("pw1").setIndex("pieces", "2*Dt+Rt", 3, 0);
    model.component("comp1").func("pw1").setIndex("pieces", "2*(Dt+Rt)", 3, 1);
    model.component("comp1").func("pw1").setIndex("pieces", "2*edot*Dt", 3, 2);
    model.component("comp1").func("pw1").setIndex("pieces", "2*(Dt+Rt)", 4, 0);
    model.component("comp1").func("pw1").setIndex("pieces", "2*(Dt+Rt)+2*Dt/3", 4, 1);
    model.component("comp1").func("pw1").setIndex("pieces", "edot*(time-2*Rt)", 4, 2);
    model.component("comp1").func("pw1").setIndex("pieces", "2*(Dt+Rt)+2*Dt/3", 5, 0);
    model.component("comp1").func("pw1").setIndex("pieces", "2*(Dt+Rt)+4*Dt/3", 5, 1);
    model.component("comp1").func("pw1").setIndex("pieces", "1+edot*(2*(Dt+Rt)-time)", 5, 2);
    model.component("comp1").func("pw1").setIndex("pieces", "2*(Dt+Rt)+4*Dt/3", 6, 0);
    model.component("comp1").func("pw1").setIndex("pieces", "2*Dt+3*Rt+4*Dt/3", 6, 1);
    model.component("comp1").func("pw1").setIndex("pieces", "2*edot*Dt", 6, 2);
    model.component("comp1").func("pw1").setIndex("pieces", "2*Dt+3*Rt+4*Dt/3", 7, 0);
    model.component("comp1").func("pw1").setIndex("pieces", "3*(Dt+Rt)+4*Dt/3", 7, 1);
    model.component("comp1").func("pw1").setIndex("pieces", "0.4+edot*(3*Rt+4*Dt-time)", 7, 2);
    model.component("comp1").func("pw1").setIndex("pieces", "3*(Dt+Rt)+4*Dt/3", 8, 0);
    model.component("comp1").func("pw1").setIndex("pieces", "3*Dt+4*Rt+4*Dt/3", 8, 1);
    model.component("comp1").func("pw1").setIndex("pieces", "edot*Dt", 8, 2);
    model.component("comp1").func("pw1").setIndex("pieces", "3*Dt+4*Rt+4*Dt/3", 9, 0);
    model.component("comp1").func("pw1").setIndex("pieces", "4*(Dt+Rt)+4*Dt/3", 9, 1);
    model.component("comp1").func("pw1").setIndex("pieces", "0.4+edot*(4*Rt+4*Dt-time)", 9, 2);
    model.component("comp1").func("pw1").set("argunit", "s");
    model.component("comp1").func("pw1").set("fununit", "1");
    model.component("comp1").func("pw1").label("\u5bf9\u6570\u5e94\u53d8");

    model.component("comp1").cpl().create("aveop1", "Average");

    model.component("comp1").geom("geom1").run();

    model.component("comp1").cpl("aveop1").set("axisym", true);
    model.component("comp1").cpl("aveop1").selection().geom("geom1", 1);
    model.component("comp1").cpl("aveop1").selection().set(3);
    model.component("comp1").cpl("aveop1").label("\u9876\u9762\u5e73\u5747\u503c");

    model.component("comp1").physics("solid").prop("StructuralTransientBehavior")
         .set("StructuralTransientBehavior", "Quasistatic");
    model.component("comp1").physics("solid").create("symp1", "SymmetryPlane", 1);
    model.component("comp1").physics("solid").feature("symp1").selection().set(2);
    model.component("comp1").physics("solid").create("hmm1", "HyperelasticModel", 2);
    model.component("comp1").physics("solid").feature("hmm1").set("MaterialModel", "ArrudaBoyce");
    model.component("comp1").physics("solid").feature("hmm1")
         .set("Compressibility_ArrudaBoyce", "CompressibleUncoupled");
    model.component("comp1").physics("solid").feature("hmm1").set("VolumetricEnergyUncoupled", "miehe");
    model.component("comp1").physics("solid").feature("hmm1").selection().set(1);
    model.component("comp1").physics("solid").feature("hmm1").create("pvp1", "PolymerViscoplasticity", 2);
    model.component("comp1").physics("solid").feature("hmm1").feature("pvp1").set("betav", 1.6);
    model.component("comp1").physics("solid").feature("hmm1").feature("pvp1").set("timeMethod", "ode");
    model.component("comp1").physics("solid").create("disp1", "Displacement1", 1);
    model.component("comp1").physics("solid").feature("disp1").setIndex("Direction", "prescribed", 2);
    model.component("comp1").physics("solid").feature("disp1").setIndex("U0", "0.5*H*(exp(-pw1(t))-1)", 2);
    model.component("comp1").physics("solid").feature("disp1").selection().set(3);

    model.component("comp1").material().create("mat1", "Common");
    model.component("comp1").material("mat1").propertyGroup().create("KG", "KG", "Bulk_modulus_and_shear_modulus");
    model.component("comp1").material("mat1").propertyGroup("KG").set("K", new String[]{"2[GPa]"});
    model.component("comp1").material("mat1").propertyGroup().create("ArrudaBoyce", "ArrudaBoyce", "Arruda-Boyce");
    model.component("comp1").material("mat1").propertyGroup("ArrudaBoyce").set("Nseg", new String[]{"8"});
    model.component("comp1").material("mat1").propertyGroup("ArrudaBoyce").set("mu0", new String[]{"0.6[MPa]"});
    model.component("comp1").material("mat1").propertyGroup("def").set("density", new String[]{"0"});
    model.component("comp1").material("mat1").propertyGroup()
         .create("BergstromBoyce", "BergstromBoyce", "Bergstrom-Boyce_viscoplasticity");
    model.component("comp1").material("mat1").propertyGroup("BergstromBoyce")
         .set("A_BB", new String[]{"7*sqrt(2/3)[1/s]"});
    model.component("comp1").material("mat1").propertyGroup("BergstromBoyce")
         .set("sigRes_BB", new String[]{"sqrt(3)[MPa]"});
    model.component("comp1").material("mat1").propertyGroup("BergstromBoyce").set("n_BB", new String[]{"4"});
    model.component("comp1").material("mat1").propertyGroup("BergstromBoyce").set("sigmaco_BB", new String[]{"0"});
    model.component("comp1").material("mat1").propertyGroup("BergstromBoyce").set("c_BB", new String[]{"-1"});

    model.component("comp1").mesh("mesh1").create("map1", "Map");
    model.component("comp1").mesh("mesh1").feature("map1").create("dis1", "Distribution");
    model.component("comp1").mesh("mesh1").feature("map1").feature("dis1").selection().set(2, 4);
    model.component("comp1").mesh("mesh1").feature("map1").feature("dis1").set("numelem", 1);
    model.component("comp1").mesh("mesh1").run();

    model.study("std1").label("\u975e\u5e73\u8861\u5efa\u6a21");
    model.study("std1").setGenPlots(false);
    model.study("std1").feature("time").set("tlist", "range(0,8,endTime)");
    model.study("std1").showAutoSequences("all");

    model.sol("sol1").feature("v1").feature("comp1_u").set("scaleval", 0.005);
    model.sol("sol1").feature("v1").feature("comp1_solid_hmm1_pvp1_evpe").set("scaleval", 1);
    model.sol("sol1").feature("v1").feature("comp1_solid_hmm1_pvp1_evp").set("scaleval", 1);
    model.sol("sol1").feature("t1").set("tstepsbdf", "strict");

    model.study("std1").createAutoSequences("all");

    model.sol("sol1").runAll();

    model.result().configuration().create("prfu1", "PreferredUnits");
    model.result().configuration("prfu1")
         .setIndex("quantityunits", new String[]{"stress", "\u5e94\u529b\u5f20\u91cf", "N/m^2", "N/m^2"}, 0);
    model.result().configuration("prfu1").setIndex("quantityunits", "MPa", 0, 3);
    model.result().configuration("prfu1").set("applytosamedims", true);
    model.result().configuration("prfu1").apply();
    model.result().dataset().create("rev1", "Revolve2D");
    model.result().dataset().create("mir1", "Mirror3D");
    model.result().dataset("mir1").set("quickplane", "xy");
    model.result().create("pg1", "PlotGroup3D");
    model.result("pg1").run();
    model.result("pg1").label("\u4f4d\u79fb");
    model.result("pg1").set("data", "mir1");
    model.result("pg1").set("titletype", "manual");
    model.result("pg1").set("title", "\u4f4d\u79fb\u5927\u5c0f [mm]");
    model.result("pg1").set("paramindicator", "Time=eval(t) s");
    model.result("pg1").create("vol1", "Volume");
    model.result("pg1").feature("vol1").set("colortable", "SpectrumLight");
    model.result("pg1").feature("vol1").create("def1", "Deform");
    model.result("pg1").feature("vol1").feature("def1").set("revcoordsys", "cylindrical");
    model.result("pg1").run();
    model.result("pg1").feature("vol1").feature("def1").set("scaleactive", true);
    model.result("pg1").feature("vol1").feature("def1").set("scale", 1);
    model.result("pg1").run();
    model.result("pg1").run();
    model.result().create("pg2", "PlotGroup1D");
    model.result("pg2").run();
    model.result("pg2").label("\u62c9\u4f38\u5386\u7a0b");
    model.result("pg2").set("titletype", "manual");
    model.result("pg2").set("title", "\u53d8\u5f62\u68af\u5ea6");
    model.result("pg2").set("ylabelactive", true);
    model.result("pg2").set("ylabel", "\u62c9\u4f38 (1)");
    model.result("pg2").create("glob1", "Global");
    model.result("pg2").feature("glob1").set("markerpos", "datapoints");
    model.result("pg2").feature("glob1").set("linewidth", "preference");
    model.result("pg2").feature("glob1").setIndex("expr", "aveop1(solid.FdzZ)", 0);
    model.result("pg2").feature("glob1").setIndex("descr", "\u603b\u62c9\u4f38", 0);
    model.result("pg2").feature("glob1").setIndex("expr", "aveop1(solid.hmm1.pvp1.Fvpl33)", 1);
    model.result("pg2").feature("glob1").setIndex("descr", "\u9ecf\u5851\u6027\u62c9\u4f38", 1);
    model.result("pg2").feature("glob1").setIndex("expr", "aveop1(solid.hmm1.pvp1.Fvpil33*solid.FdzZ)", 2);
    model.result("pg2").feature("glob1").setIndex("descr", "\u5f39\u6027\u62c9\u4f38", 2);
    model.result("pg2").feature("glob1").set("linewidth", 2);
    model.result("pg2").run();
    model.result("pg2").set("legendpos", "center");
    model.result("pg2").run();
    model.result().create("pg3", "PlotGroup1D");
    model.result("pg3").run();
    model.result("pg3").label("\u771f\u5b9e\u5e94\u529b");
    model.result("pg3").set("titletype", "manual");
    model.result("pg3").set("title", "\u771f\u5b9e\u5e94\u529b");
    model.result("pg3").set("xlabelactive", true);
    model.result("pg3").set("xlabel", "\u771f\u5b9e\u5e94\u53d8 (1)");
    model.result("pg3").set("ylabelactive", true);
    model.result("pg3").set("ylabel", "\u771f\u5b9e\u5e94\u529b (MPa)");
    model.result("pg3").create("glob1", "Global");
    model.result("pg3").feature("glob1").set("markerpos", "datapoints");
    model.result("pg3").feature("glob1").set("linewidth", "preference");
    model.result("pg3").feature("glob1").setIndex("expr", "aveop1(-solid.szz)", 0);
    model.result("pg3").feature("glob1").set("xdata", "expr");
    model.result("pg3").feature("glob1").set("xdataexpr", "aveop1(abs(solid.elogzz))");
    model.result("pg3").feature("glob1").set("linewidth", 2);
    model.result("pg3").feature("glob1").set("legendmethod", "manual");
    model.result("pg3").feature("glob1").setIndex("legends", "Comsol", 0);
    model.result("pg3").run();
    model.result("pg3").set("legendpos", "upperleft");
    model.result().table().create("tbl1", "Table");
    model.result().table("tbl1").label("\u53c2\u8003\u7ed3\u679c");
    model.result().table("tbl1").importData("chloroprene_rubber_compression_test_numerical.txt");
    model.result("pg3").run();
    model.result("pg3").create("tblp1", "Table");
    model.result("pg3").feature("tblp1").set("markerpos", "datapoints");
    model.result("pg3").feature("tblp1").set("linewidth", "preference");
    model.result("pg3").feature("tblp1").set("linestyle", "none");
    model.result("pg3").feature("tblp1").set("linemarker", "circle");
    model.result("pg3").feature("tblp1").set("legend", true);
    model.result("pg3").feature("tblp1").set("legendmethod", "manual");
    model.result("pg3").feature("tblp1").setIndex("legends", "\u53c2\u8003\u8d44\u6599", 0);
    model.result().table().create("tbl2", "Table");
    model.result().table("tbl2").label("\u5b9e\u9a8c\u7ed3\u679c");
    model.result().table("tbl2").importData("chloroprene_rubber_compression_test_experimental.txt");
    model.result("pg3").run();
    model.result("pg3").create("tblp2", "Table");
    model.result("pg3").feature("tblp2").set("markerpos", "datapoints");
    model.result("pg3").feature("tblp2").set("linewidth", "preference");
    model.result("pg3").feature("tblp2").set("table", "tbl2");
    model.result("pg3").feature("tblp2").set("linestyle", "none");
    model.result("pg3").feature("tblp2").set("linemarker", "circle");
    model.result("pg3").feature("tblp2").set("legend", true);
    model.result("pg3").feature("tblp2").set("legendmethod", "manual");
    model.result("pg3").feature("tblp2").setIndex("legends", "\u5b9e\u9a8c", 0);
    model.result("pg3").run();
    model.result("pg3").run();
    model.result("pg3").feature("glob1").create("comp1", "Comparison");
    model.result("pg3").run();
    model.result("pg3").feature("glob1").feature("comp1").set("metric", "r2");
    model.result("pg3").run();
    model.result().create("pg4", "PlotGroup1D");
    model.result("pg4").run();
    model.result("pg4").label("\u975e\u5f39\u6027\u529b\u8d21\u732e");
    model.result("pg4").set("titletype", "manual");
    model.result("pg4")
         .set("title", "\u5f52\u4e00\u5316\u975e\u5f39\u6027\u529b\uff1aP<SUB>33</SUB>A<SUB>0</SUB>/max(P<SUB>33</SUB>A<SUB>0</SUB>)");
    model.result("pg4").set("ylabelactive", true);
    model.result("pg4").set("ylabel", "\u5f52\u4e00\u5316\u529b (1)");
    model.result("pg4").create("glob1", "Global");
    model.result("pg4").feature("glob1").set("markerpos", "datapoints");
    model.result("pg4").feature("glob1").set("linewidth", "preference");
    model.result("pg4").feature("glob1").setIndex("expr", "aveop1(abs(solid.elogzz))", 0);
    model.result("pg4").feature("glob1").setIndex("descr", "\u771f\u5b9e\u5e94\u53d8", 0);
    model.result("pg4").feature("glob1").set("linewidth", 2);
    model.result().numerical().create("pev1", "EvalPoint");
    model.result().numerical("pev1").label("\u6700\u5927\u975e\u5f39\u6027\u529b");
    model.result().numerical("pev1").selection().set(2);
    model.result().numerical("pev1").setIndex("expr", "timemax(0,endTime,abs(solid.Fdlz3*solid.Siel33*A0))", 0);
    model.result().numerical("pev1").setIndex("looplevelinput", "first", 0);
    model.result().table().create("tbl3", "Table");
    model.result().table("tbl3").comments("\u6700\u5927\u975e\u5f39\u6027\u529b");
    model.result().numerical("pev1").set("table", "tbl3");
    model.result().numerical("pev1").setResult();
    model.result("pg4").run();
    model.result("pg4").feature("glob1").setIndex("expr", "aveop1(abs(solid.Fdlz3*solid.Siel33*A0)/146.75[N])", 1);
    model.result("pg4").feature("glob1").setIndex("descr", "\u975e\u5f39\u6027\u529b", 1);
    model.result("pg4").run();

    model.study().create("std2");
    model.study("std2").create("time", "Transient");
    model.study("std2").feature("time").setSolveFor("/physics/solid", true);
    model.study("std2").label("\u5e73\u8861\u5efa\u6a21");
    model.study("std2").setGenPlots(false);
    model.study("std2").feature("time").set("tlist", "range(0,8,endTime)");
    model.study("std2").feature("time").set("useadvanceddisable", true);
    model.study("std2").feature("time").set("disabledphysics", new String[]{"solid/hmm1/pvp1"});
    model.study("std2").showAutoSequences("all");

    model.sol("sol2").feature("t1").set("tstepsbdf", "strict");
    model.sol("sol2").feature("t1").feature("fc1").set("dtech", "auto");
    model.sol("sol2").feature("v1").feature("comp1_u").set("scaleval", 0.005);

    model.study("std2").createAutoSequences("all");

    model.sol("sol2").runAll();

    model.result().create("pg5", "PlotGroup1D");
    model.result("pg5").run();
    model.result("pg5").label("\u975e\u5e73\u8861 vs. \u5e73\u8861");
    model.result("pg5").set("titletype", "manual");
    model.result("pg5").set("title", "\u771f\u5b9e\u5e94\u529b");
    model.result("pg5").set("xlabelactive", true);
    model.result("pg5").set("xlabel", "\u771f\u5b9e\u5e94\u53d8 (1)");
    model.result("pg5").set("ylabelactive", true);
    model.result("pg5").set("ylabel", "\u771f\u5b9e\u5e94\u529b (MPa)");
    model.result("pg5").set("legendpos", "upperleft");
    model.result("pg5").create("glob1", "Global");
    model.result("pg5").feature("glob1").set("markerpos", "datapoints");
    model.result("pg5").feature("glob1").set("linewidth", "preference");
    model.result("pg5").feature("glob1").label("\u975e\u5e73\u8861");
    model.result("pg5").feature("glob1").setIndex("expr", "aveop1(-solid.szz)", 0);
    model.result("pg5").feature("glob1").set("linewidth", 2);
    model.result("pg5").feature("glob1").set("autoplotlabel", true);
    model.result("pg5").feature("glob1").set("autosolution", false);
    model.result("pg5").feature("glob1").set("autodescr", false);
    model.result("pg5").feature("glob1").set("xdata", "expr");
    model.result("pg5").feature("glob1").set("xdataexpr", "aveop1(abs(solid.elogzz))");
    model.result("pg5").feature().duplicate("glob2", "glob1");
    model.result("pg5").run();
    model.result("pg5").feature("glob2").label("\u5e73\u8861");
    model.result("pg5").feature("glob2").set("data", "dset2");
    model.result("pg5").run();
    model.result("pg5").feature().duplicate("glob3", "glob1");
    model.result("pg5").run();
    model.result("pg5").feature("glob3").label("\u5dee\u96c6");
    model.result("pg5").feature("glob3")
         .setIndex("expr", "aveop1(-solid.szz)-withsol('sol2',aveop1(-solid.szz),setval(t,t))", 0);
    model.result("pg5").run();

    model.component("comp1").func("pw1").createPlot("pg6");

    model.result("pg6").run();
    model.result("pg6").label("\u771f\u5b9e\u5e94\u53d8\u5386\u7a0b");
    model.result("pg6").set("titletype", "manual");
    model.result("pg6").set("title", "\u771f\u5b9e\u5e94\u53d8\u5386\u7a0b");
    model.result("pg6").set("xlabelactive", true);
    model.result("pg6").set("xlabel", "\u65f6\u95f4 (s)");
    model.result("pg6").set("ylabelactive", true);
    model.result("pg6").set("ylabel", "\u771f\u5b9e\u5e94\u53d8 (1)");
    model.result("pg6").set("axislimits", true);
    model.result("pg6").set("xmin", 0);
    model.result("pg6").set("xmax", "endTime");
    model.result("pg6").set("manualgrid", true);
    model.result("pg6").set("xspacing", 100);
    model.result("pg6").set("yspacing", 0.2);
    model.result("pg6").run();
    model.result("pg6").feature("plot1").set("linewidth", 2);
    model.result("pg6").run();
    model.result("pg6").run();
    model.result().remove("pg6");
    model.result().dataset().remove("pw1_ds1");
    model.result("pg5").run();

    model.title("\u6c2f\u4e01\u6a61\u80f6\u538b\u7f29\u8bd5\u9a8c");

    model
         .description("\u672c\u4f8b\u4f7f\u7528 Bergstrom-Boyce \u6750\u6599\u6a21\u578b\u6355\u83b7\u70ad\u9ed1\u586b\u5145\u6c2f\u4e01\u6a61\u80f6\u5728\u538b\u7f29\u4e0e\u677e\u5f1b\u4ea4\u66ff\u7684\u5e94\u53d8\u5386\u53f2\u4e0b\u7684\u975e\u5e73\u8861\u7279\u6027\u3002\u4eff\u771f\u7ed3\u679c\u6839\u636e\u6587\u732e\u4e2d\u7684\u5b9e\u9a8c\u548c\u6570\u503c\u7ed3\u679c\u8fdb\u884c\u4e86\u9a8c\u8bc1\u3002");

    model.mesh().clearMeshes();

    model.sol("sol1").clearSolutionData();
    model.sol("sol2").clearSolutionData();

    model.label("chloroprene_rubber_compression_test.mph");

    return model;
  }

  public static void main(String[] args) {
    run();
  }

}
