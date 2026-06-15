/*
 * cosserat_torsion.java
 */

import com.comsol.model.*;
import com.comsol.model.util.*;

/** Model exported on Apr 30 2026, 13:12 by COMSOL 6.3.0.290. */
public class cosserat_torsion {

  public static Model run() {
    Model model = ModelUtil.create("Model");

    model
         .modelPath("D:\\Program Files\\COMSOL\\COMSOL63\\Multiphysics\\applications\\Structural_Mechanics_Module\\Material_Models");

    model.component().create("comp1", true);

    model.component("comp1").geom().create("geom1", 3);
    model.component("comp1").geom("geom1").geomRep("comsol");

    model.component("comp1").mesh().create("mesh1");
    model.component("comp1").mesh("mesh1").contribute("geom/detail", true);

    model.component("comp1").physics().create("solid", "SolidMechanics", "geom1");

    model.study().create("std1");
    model.study("std1").create("stat", "Stationary");
    model.study("std1").feature("stat").setSolveFor("/physics/solid", true);

    model.param().label("\u901a\u7528\u53c2\u6570");
    model.param().set("R0", "1[mm]");
    model.param().descr("R0", "\u534a\u5f84");
    model.param().set("L0", "10[mm]");
    model.param().descr("L0", "\u957f\u5ea6");
    model.param().set("E0", "1e6[MPa]");
    model.param().descr("E0", "\u6768\u6c0f\u6a21\u91cf");
    model.param().set("Nu0", "0.3");
    model.param().descr("Nu0", "\u6cca\u677e\u6bd4");
    model.param().set("Theta0", "14[deg]");
    model.param().descr("Theta0", "\u65bd\u52a0\u7684\u65cb\u8f6c");
    model.param().set("mu0", "E0/(2*(1+Nu0))");
    model.param().descr("mu0", "\u526a\u5207\u6a21\u91cf");
    model.param().create("par2");
    model.param("par2").label("Cosserat \u53c2\u6570");
    model.param("par2").set("muC", "0.01*mu0");
    model.param("par2").descr("muC", "Cosserat \u8026\u5408\u6a21\u91cf");
    model.param("par2").set("LcR0", "100");
    model.param("par2").descr("LcR0", "\u5185\u90e8\u957f\u5ea6\u5c3a\u5ea6\u53c2\u6570");
    model.param("par2").set("gammaC", "mu0*(LcR0*R0)^2");
    model.param("par2").descr("gammaC", "\u7b2c\u4e09\u4e2a\u5fae\u65cb\u8f6c\u53c2\u6570");

    model.component("comp1").geom("geom1").lengthUnit("mm");
    model.component("comp1").geom("geom1").create("cyl1", "Cylinder");
    model.component("comp1").geom("geom1").feature("cyl1").set("r", "R0");
    model.component("comp1").geom("geom1").feature("cyl1").set("h", "L0");
    model.component("comp1").geom("geom1").run("cyl1");
    model.component("comp1").geom("geom1").run();

    model.component("comp1").material().create("mat1", "Common");
    model.component("comp1").material("mat1").propertyGroup()
         .create("Enu", "Enu", "Young's_modulus_and_Poisson's_ratio");
    model.component("comp1").material("mat1").propertyGroup("Enu").set("E", new String[]{"E0"});
    model.component("comp1").material("mat1").propertyGroup("Enu").set("nu", new String[]{"Nu0"});
    model.component("comp1").material("mat1").propertyGroup("def").set("density", new String[]{"1"});

    model.component("comp1").physics().create("w", "WeakFormPDE", "geom1");

    model.study("std1").feature("stat").setSolveFor("/physics/w", true);

    model.component("comp1").physics("w").prop("EquationForm").set("form", "Automatic");
    model.component("comp1").physics("w").field("dimensionless").field("a");
    model.component("comp1").physics("w").field("dimensionless").component(new String[]{"a1", "a2", "a3"});
    model.component("comp1").physics("w").feature("wfeq1")
         .set("weak", new String[][]{{"-test(a1x)*a1x-test(a1y)*a1y-test(a1z)*a1z+1[m^-2]*test(a1)"}, {"-test(a2x)*a2x-test(a2y)*a2y-test(a2z)*a2z+1[m^-2]*test(a2)"}, {"-test(a3x)*a3x-test(a3y)*a3y-test(a3z)*a3z+1[m^-2]*test(a3)"}});
    model.component("comp1").physics("w").prop("ShapeProperty").set("order", 1);
    model.component("comp1").physics("w").prop("ShapeProperty").set("frame", "material");
    model.component("comp1").physics("w").label("\u5fae\u65cb\u8f6c\u573a");

    model.component("comp1").variable().create("var1");
    model.component("comp1").variable("var1").label("\u5b8f\u65cb\u8f6c\u77e2\u91cf");
    model.component("comp1").variable("var1").set("w1", "solid.curlUX");
    model.component("comp1").variable("var1").descr("w1", "\u4f4d\u79fb\u65cb\u5ea6\uff0c1 \u5206\u91cf");
    model.component("comp1").variable("var1").set("w2", "solid.curlUY");
    model.component("comp1").variable("var1").descr("w2", "\u4f4d\u79fb\u65cb\u5ea6\uff0c2 \u5206\u91cf");
    model.component("comp1").variable("var1").set("w3", "solid.curlUZ");
    model.component("comp1").variable("var1").descr("w3", "\u4f4d\u79fb\u65cb\u5ea6\uff0c3 \u5206\u91cf");
    model.component("comp1").variable("var1").set("r1", "w1/2");
    model.component("comp1").variable("var1").descr("r1", "\u5b8f\u65cb\u8f6c\u77e2\u91cf\uff0c1 \u5206\u91cf");
    model.component("comp1").variable("var1").set("r2", "w2/2");
    model.component("comp1").variable("var1").descr("r2", "\u5b8f\u65cb\u8f6c\u77e2\u91cf\uff0c2 \u5206\u91cf");
    model.component("comp1").variable("var1").set("r3", "w3/2");
    model.component("comp1").variable("var1").descr("r3", "\u5b8f\u65cb\u8f6c\u77e2\u91cf\uff0c3 \u5206\u91cf");

    model.component("comp1").common().create("mat1", "Matrix");
    model.component("comp1").common("mat1").setIndex("matrix", 0, 0, 0);
    model.component("comp1").common("mat1").setIndex("matrix", "-r3", 0, 1);
    model.component("comp1").common("mat1").setIndex("matrix", "r2", 0, 2);
    model.component("comp1").common("mat1").setIndex("matrix", "r3", 1, 0);
    model.component("comp1").common("mat1").setIndex("matrix", 0, 1, 1);
    model.component("comp1").common("mat1").setIndex("matrix", "-r1", 1, 2);
    model.component("comp1").common("mat1").setIndex("matrix", "-r2", 2, 0);
    model.component("comp1").common("mat1").setIndex("matrix", "r1", 2, 1);
    model.component("comp1").common("mat1").setIndex("matrix", 0, 2, 2);
    model.component("comp1").common("mat1").label("\u5b8f\u65cb\u8f6c");
    model.component("comp1").common("mat1").set("name", "W");
    model.component("comp1").common().create("mat2", "Matrix");
    model.component("comp1").common("mat2").label("\u5fae\u65cb\u8f6c");
    model.component("comp1").common("mat2").set("name", "A");
    model.component("comp1").common("mat2").setIndex("matrix", 0, 0, 0);
    model.component("comp1").common("mat2").setIndex("matrix", "-a3", 0, 1);
    model.component("comp1").common("mat2").setIndex("matrix", "a2", 0, 2);
    model.component("comp1").common("mat2").setIndex("matrix", "a3", 1, 0);
    model.component("comp1").common("mat2").setIndex("matrix", 0, 1, 1);
    model.component("comp1").common("mat2").setIndex("matrix", "-a1", 1, 2);
    model.component("comp1").common("mat2").setIndex("matrix", "-a2", 2, 0);
    model.component("comp1").common("mat2").setIndex("matrix", "a1", 2, 1);
    model.component("comp1").common("mat2").setIndex("matrix", 0, 2, 2);
    model.component("comp1").common().create("mat3", "Matrix");
    model.component("comp1").common("mat3").setIndex("matrix", "gammaC*a1X", 0, 0);
    model.component("comp1").common("mat3").setIndex("matrix", "gammaC*a1Y", 0, 1);
    model.component("comp1").common("mat3").setIndex("matrix", "gammaC*a1Z", 0, 2);
    model.component("comp1").common("mat3").setIndex("matrix", "gammaC*a2X", 1, 0);
    model.component("comp1").common("mat3").setIndex("matrix", "gammaC*a2Y", 1, 1);
    model.component("comp1").common("mat3").setIndex("matrix", "gammaC*a2Z", 1, 2);
    model.component("comp1").common("mat3").setIndex("matrix", "gammaC*a3X", 2, 0);
    model.component("comp1").common("mat3").setIndex("matrix", "gammaC*a3Y", 2, 1);
    model.component("comp1").common("mat3").setIndex("matrix", "gammaC*a3Z", 2, 2);
    model.component("comp1").common("mat3").label("\u5e94\u529b\u77e9\u5f20\u91cf");
    model.component("comp1").common("mat3").set("name", "M");
    model.component("comp1").common().create("mat4", "Matrix");
    model.component("comp1").common("mat4").label("\u4e0d\u5bf9\u79f0\u5e94\u529b\u5f20\u91cf");
    model.component("comp1").common("mat4").set("name", "Pc");
    model.component("comp1").common("mat4").setIndex("matrix", "2*muC*(W11-A11)", 0, 0);
    model.component("comp1").common("mat4").setIndex("matrix", "2*muC*(W12-A12)", 0, 1);
    model.component("comp1").common("mat4").setIndex("matrix", "2*muC*(W13-A13)", 0, 2);
    model.component("comp1").common("mat4").setIndex("matrix", "2*muC*(W21-A21)", 1, 0);
    model.component("comp1").common("mat4").setIndex("matrix", "2*muC*(W22-A22)", 1, 1);
    model.component("comp1").common("mat4").setIndex("matrix", "2*muC*(W23-A23)", 1, 2);
    model.component("comp1").common("mat4").setIndex("matrix", "2*muC*(W31-A31)", 2, 0);
    model.component("comp1").common("mat4").setIndex("matrix", "2*muC*(W32-A32)", 2, 1);
    model.component("comp1").common("mat4").setIndex("matrix", "2*muC*(W33-A33)", 2, 2);

    model.component("comp1").physics("solid").feature("lemm1").create("exs1", "ExternalStress", 3);
    model.component("comp1").physics("solid").feature("lemm1").feature("exs1")
         .set("StressInputType", "StressTensorNominal");
    model.component("comp1").physics("solid").feature("lemm1").feature("exs1")
         .set("Pext", new String[]{"Pc11", "Pc21", "Pc31", "Pc12", "Pc22", "Pc32", "Pc13", "Pc23", "Pc33"});
    model.component("comp1").physics("solid").create("fix1", "Fixed", 2);
    model.component("comp1").physics("solid").feature("fix1").selection().set(3);
    model.component("comp1").physics("solid").create("rig1", "RigidConnector", 2);
    model.component("comp1").physics("solid").feature("rig1").selection().set(4);
    model.component("comp1").physics("solid").feature("rig1")
         .set("CenterOfRotationType", "CentroidOfSelectedEntities");
    model.component("comp1").physics("solid").feature("rig1").setIndex("Direction", true, 0);
    model.component("comp1").physics("solid").feature("rig1").setIndex("Direction", true, 1);
    model.component("comp1").physics("solid").feature("rig1").setIndex("Direction", true, 2);
    model.component("comp1").physics("solid").feature("rig1").set("RotationType", "PrescribedRotationGroup");
    model.component("comp1").physics("solid").feature("rig1").set("Omega", new int[]{0, 0, 1});
    model.component("comp1").physics("solid").feature("rig1").set("phi0", "Theta0");
    model.component("comp1").physics("solid").feature("rig1").set("WeakConstraints", true);
    model.component("comp1").physics("solid").feature("rig1").feature("crb1").selection().set(4);
    model.component("comp1").physics("w").feature("wfeq1")
         .setIndex("weak", "Pc11*test(A11)+Pc12*test(A12)+Pc13*test(A13)-M11*test(a1X)-M12*test(a1Y)-M13*test(a1Z)", 0);
    model.component("comp1").physics("w").feature("wfeq1")
         .setIndex("weak", "Pc21*test(A21)+Pc22*test(A22)+Pc23*test(A23)-M21*test(a2X)-M22*test(a2Y)-M23*test(a2Z)", 1);
    model.component("comp1").physics("w").feature("wfeq1")
         .setIndex("weak", "Pc31*test(A31)+Pc32*test(A32)+Pc33*test(A33)-M31*test(a3X)-M32*test(a3Y)-M33*test(a3Z)", 2);
    model.component("comp1").physics("w").create("dir1", "DirichletBoundary", 2);
    model.component("comp1").physics("w").feature("dir1").selection().set(3);

    model.component("comp1").mesh("mesh1").create("fq1", "FreeQuad");
    model.component("comp1").mesh("mesh1").feature("fq1").selection().set(3);
    model.component("comp1").mesh("mesh1").create("swe1", "Sweep");
    model.component("comp1").mesh("mesh1").feature("swe1").create("dis1", "Distribution");
    model.component("comp1").mesh("mesh1").feature("swe1").feature("dis1").set("numelem", 30);
    model.component("comp1").mesh("mesh1").feature("size").set("hauto", 3);
    model.component("comp1").mesh("mesh1").run();

    model.study("std1").setGenPlots(false);
    model.study("std1").feature("stat").set("useparam", true);
    model.study("std1").feature("stat").setIndex("pname", "E0", 0);
    model.study("std1").feature("stat").setIndex("plistarr", "", 0);
    model.study("std1").feature("stat").setIndex("punit", "Pa", 0);
    model.study("std1").feature("stat").setIndex("pname", "E0", 0);
    model.study("std1").feature("stat").setIndex("plistarr", "", 0);
    model.study("std1").feature("stat").setIndex("punit", "Pa", 0);
    model.study("std1").feature("stat").setIndex("pname", "LcR0", 0);
    model.study("std1").feature("stat")
         .setIndex("plistarr", "1e-3 1e-2 range(0.1,0.1,1) range(2,1,10) range(20,10,100) 500 1e3 1e4", 0);
    model.study("std1").create("param", "Parametric");
    model.study("std1").feature("param").setIndex("pname", "E0", 0);
    model.study("std1").feature("param").setIndex("plistarr", "", 0);
    model.study("std1").feature("param").setIndex("punit", "Pa", 0);
    model.study("std1").feature("param").setIndex("pname", "E0", 0);
    model.study("std1").feature("param").setIndex("plistarr", "", 0);
    model.study("std1").feature("param").setIndex("punit", "Pa", 0);
    model.study("std1").feature("param").setIndex("pname", "muC", 0);
    model.study("std1").feature("param").setIndex("plistarr", "0.01*mu0 mu0 10*mu0", 0);
    model.study("std1").showAutoSequences("all");

    model.sol("sol1").feature("s1").create("fc1", "FullyCoupled");

    model.study("std1").createAutoSequences("all");

    model.sol().create("sol2");
    model.sol("sol2").study("std1");
    model.sol("sol2").label("\u53c2\u6570\u5316\u89e3 1");

    model.batch("p1").feature("so1").set("psol", "sol2");
    model.batch("p1").run("compute");

    model.result().create("pg1", "PlotGroup1D");
    model.result("pg1").run();
    model.result("pg1").label("\u626d\u77e9 vs. Lc");
    model.result("pg1").set("data", "dset2");
    model.result("pg1").set("xlabelactive", true);
    model.result("pg1").set("xlabel", "L<sub>c</sub>/R<sub>0</sub>");
    model.result("pg1").set("ylabelactive", true);
    model.result("pg1").set("ylabel", "\u53cd\u4f5c\u7528\u529b\u77e9 z [N*m]");
    model.result("pg1").set("xlog", true);
    model.result("pg1").set("legendpos", "upperleft");
    model.result("pg1").create("glob1", "Global");
    model.result("pg1").feature("glob1").set("markerpos", "datapoints");
    model.result("pg1").feature("glob1").set("linewidth", "preference");
    model.result("pg1").feature("glob1").set("expr", new String[]{"solid.rig1.RMz"});
    model.result("pg1").feature("glob1")
         .set("descr", new String[]{"\u53cd\u4f5c\u7528\u529b\u77e9\uff0cz \u5206\u91cf"});
    model.result("pg1").feature("glob1").set("unit", new String[]{"N*m"});
    model.result("pg1").feature("glob1").set("legendmethod", "manual");
    model.result("pg1").feature("glob1").setIndex("legends", "\\mu<sub>C</sub> = 0.01\\mu; \\theta = 14\\deg", 0);
    model.result("pg1").feature("glob1").setIndex("legends", "\\mu<sub>C</sub> = \\mu; \\theta = 14\\deg", 1);
    model.result("pg1").feature("glob1").setIndex("legends", "\\mu<sub>C</sub> = 10\\mu; \\theta = 14\\deg", 2);
    model.result("pg1").feature("glob1").set("linemarker", "circle");
    model.result("pg1").run();
    model.result("pg1").run();
    model.result().duplicate("pg2", "pg1");
    model.result("pg2").run();
    model.result("pg2").label("\u626d\u77e9 vs. Lc\uff0cmuC = 0.01mu");
    model.result("pg2").setIndex("looplevelinput", "first", 1);
    model.result("pg2").run();
    model.result("pg2").create("ptgr1", "PointGraph");
    model.result("pg2").feature("ptgr1").set("markerpos", "datapoints");
    model.result("pg2").feature("ptgr1").set("linewidth", "preference");
    model.result("pg2").feature("ptgr1").selection().set(4);
    model.result("pg2").feature("ptgr1").set("expr", "LcR0*0.003+13");
    model.result("pg2").feature("ptgr1").set("xdata", "expr");
    model.result("pg2").feature("ptgr1").set("xdataexpr", "0.1");
    model.result("pg2").feature("ptgr1").set("linecolor", "black");
    model.result("pg2").feature("ptgr1").set("titletype", "none");
    model.result("pg2").feature().duplicate("ptgr2", "ptgr1");
    model.result("pg2").run();
    model.result("pg2").feature("ptgr2").set("xdataexpr", "10");
    model.result("pg2").run();
    model.result("pg2").create("tlan1", "TableAnnotation");
    model.result("pg2").feature("tlan1").set("source", "localtable");
    model.result("pg2").feature("tlan1").setIndex("localtablematrix", 0.01, 0, 0);
    model.result("pg2").feature("tlan1").setIndex("localtablematrix", 20, 0, 1);
    model.result("pg2").feature("tlan1").setIndex("localtablematrix", "I \u533a", 0, 2);
    model.result("pg2").feature("tlan1").setIndex("localtablematrix", 1, 1, 0);
    model.result("pg2").feature("tlan1").setIndex("localtablematrix", 20, 1, 1);
    model.result("pg2").feature("tlan1").setIndex("localtablematrix", "II \u533a", 1, 2);
    model.result("pg2").feature("tlan1").setIndex("localtablematrix", 100, 2, 0);
    model.result("pg2").feature("tlan1").setIndex("localtablematrix", 20, 2, 1);
    model.result("pg2").feature("tlan1").setIndex("localtablematrix", "III \u533a", 2, 2);
    model.result("pg2").feature("tlan1").set("showpoint", false);
    model.result("pg2").feature("tlan1").set("showframe", true);
    model.result("pg2").run();
    model.result().create("pg3", "PlotGroup3D");
    model.result("pg3").run();
    model.result("pg3").label("\u5b8f\u65cb\u8f6c\uff0cr3");
    model.result("pg3").set("data", "none");
    model.result("pg3").set("titletype", "manual");
    model.result("pg3").set("title", "\u5b8f\u65cb\u8f6c\uff1ar3 [deg]\uff0c(\\mu<sub>C</sub>=\\mu)");
    model.result("pg3").set("paramindicator", "");
    model.result("pg3").set("showlegendsmaxmin", true);
    model.result("pg3").set("plotarrayenable", true);
    model.result("pg3").create("vol1", "Volume");
    model.result("pg3").feature("vol1").set("arraydim", "1");
    model.result("pg3").feature("vol1").set("data", "dset2");
    model.result("pg3").feature("vol1").setIndex("looplevel", 1, 0);
    model.result("pg3").feature("vol1").setIndex("looplevel", 2, 1);
    model.result("pg3").feature("vol1").set("expr", "r3");
    model.result("pg3").feature("vol1").set("unit", "deg");
    model.result("pg3").feature("vol1").set("colortable", "Prism");
    model.result("pg3").feature().duplicate("vol2", "vol1");
    model.result("pg3").feature("vol2").set("arraydim", "1");
    model.result("pg3").run();
    model.result("pg3").feature("vol2").setIndex("looplevel", 16, 0);
    model.result("pg3").feature("vol2").set("titletype", "none");
    model.result("pg3").feature("vol2").set("inheritplot", "vol1");
    model.result("pg3").feature().duplicate("vol3", "vol2");
    model.result("pg3").feature("vol3").set("arraydim", "1");
    model.result("pg3").run();
    model.result("pg3").feature("vol3").setIndex("looplevel", 33, 0);
    model.result("pg3").run();
    model.result("pg3").run();
    model.result("pg3").create("ann1", "Annotation");
    model.result("pg3").feature("ann1").set("arraydim", "1");
    model.result("pg3").feature("ann1").set("data", "dset2");
    model.result("pg3").feature("ann1").set("poszexpr", "L0/2");
    model.result("pg3").feature("ann1").set("latexmarkup", true);
    model.result("pg3").feature("ann1").set("text", "\\textbf{\\unicode{I \u533a}}");
    model.result("pg3").feature("ann1").set("showpoint", false);
    model.result("pg3").feature("ann1").set("anchorpoint", "uppermiddle");
    model.result("pg3").feature("ann1").set("manualindexing", true);
    model.result("pg3").feature().duplicate("ann2", "ann1");
    model.result("pg3").feature("ann2").set("arraydim", "1");
    model.result("pg3").run();
    model.result("pg3").feature("ann2").set("text", "\\textbf{\\unicode{II \u533a}}");
    model.result("pg3").feature("ann2").set("arrayindex", 1);
    model.result("pg3").feature().duplicate("ann3", "ann2");
    model.result("pg3").feature("ann3").set("arraydim", "1");
    model.result("pg3").run();
    model.result("pg3").feature("ann3").set("text", "\\textbf{\\unicode{III \u533a}}");
    model.result("pg3").feature("ann3").set("arrayindex", 2);
    model.result("pg3").run();

    model.view("view2").set("showgrid", false);

    model.result("pg3").run();
    model.result().duplicate("pg4", "pg3");
    model.result("pg4").run();
    model.result("pg4").label("\u5fae\u65cb\u8f6c\uff0ca3");
    model.result("pg4").set("title", "\u5fae\u65cb\u8f6c\uff1aa3 [deg]\uff0c(\\mu<sub>C</sub>=\\mu)");
    model.result("pg4").feature("vol1").set("arraydim", "1");
    model.result("pg4").run();
    model.result("pg4").feature("vol1").set("expr", "a3");
    model.result("pg4").feature("vol2").set("arraydim", "1");
    model.result("pg4").run();
    model.result("pg4").feature("vol2").set("expr", "a3");
    model.result("pg4").feature("vol3").set("arraydim", "1");
    model.result("pg4").run();
    model.result("pg4").feature("vol3").set("expr", "a3");
    model.result("pg4").run();

    model.view("view3").set("showgrid", false);

    model.result().create("pg5", "PlotGroup3D");
    model.result("pg5").run();
    model.result("pg5").label("\u4f4d\u79fb\uff0c\u7bad\u5934\u56fe");
    model.result("pg5").set("titletype", "custom");
    model.result("pg5").set("solutionintitle", false);
    model.result("pg5").create("arwv1", "ArrowVolume");
    model.result("pg5").run();
    model.result("pg5").set("plotarrayenable", true);
    model.result("pg5").feature("arwv1").set("arraydim", "1");
    model.result("pg5").run();
    model.result("pg5").feature("arwv1").set("data", "dset2");
    model.result("pg5").feature("arwv1").setIndex("looplevel", 2, 1);
    model.result("pg5").feature("arwv1").setIndex("looplevel", 1, 0);
    model.result("pg5").feature("arwv1").set("znumber", 10);
    model.result("pg5").feature("arwv1").set("scaleactive", true);
    model.result("pg5").feature("arwv1").set("scale", 3);
    model.result("pg5").run();
    model.result("pg5").feature().duplicate("arwv2", "arwv1");
    model.result("pg5").feature("arwv2").set("arraydim", "1");
    model.result("pg5").run();
    model.result("pg5").feature("arwv2").setIndex("looplevel", 16, 0);
    model.result("pg5").feature("arwv2").set("titletype", "none");
    model.result("pg5").feature("arwv2").set("inheritplot", "arwv1");
    model.result("pg5").feature().duplicate("arwv3", "arwv2");
    model.result("pg5").feature("arwv3").set("arraydim", "1");
    model.result("pg5").run();
    model.result("pg5").feature("arwv3").setIndex("looplevel", 33, 0);
    model.result("pg5").run();
    model.result("pg5").create("tlan1", "TableAnnotation");
    model.result("pg5").feature("tlan1").set("arraydim", "1");
    model.result("pg5").feature("tlan1").set("latexmarkup", true);
    model.result("pg5").feature("tlan1").set("source", "localtable");
    model.result("pg5").feature("tlan1").setIndex("localtablematrix", 0, 0, 0);
    model.result("pg5").feature("tlan1").setIndex("localtablematrix", 0, 0, 1);
    model.result("pg5").feature("tlan1").setIndex("localtablematrix", "-L0/10", 0, 2);
    model.result("pg5").feature("tlan1").setIndex("localtablematrix", "$L_\\textrm{C}/R_\\textrm{0}$ = 1e-3", 0, 3);
    model.result("pg5").feature("tlan1").setIndex("localtablematrix", "2.5*R0", 1, 0);
    model.result("pg5").feature("tlan1").setIndex("localtablematrix", 0, 1, 1);
    model.result("pg5").feature("tlan1").setIndex("localtablematrix", "-L0/10", 1, 2);
    model.result("pg5").feature("tlan1").setIndex("localtablematrix", "$L_\\textrm{C}/R_\\textrm{0}$ = 5", 1, 3);
    model.result("pg5").feature("tlan1").setIndex("localtablematrix", "5*R0", 2, 0);
    model.result("pg5").feature("tlan1").setIndex("localtablematrix", 0, 2, 1);
    model.result("pg5").feature("tlan1").setIndex("localtablematrix", "-L0/10", 2, 2);
    model.result("pg5").feature("tlan1").setIndex("localtablematrix", "$L_\\textrm{C}/R_\\textrm{0}$ = 1e4", 2, 3);
    model.result("pg5").feature("tlan1").set("showframe", true);
    model.result("pg5").run();

    model.component("comp1").view("view1").set("showgrid", false);

    model.title("\u5404\u5411\u540c\u6027 Cosserat \u5f39\u6027\u5706\u67f1\u7684\u626d\u8f6c");

    model
         .description("\u672c\u6a21\u578b\u6f14\u793a\u5982\u4f55\u901a\u8fc7\u589e\u52a0\u5fae\u65cb\u8f6c\u81ea\u7531\u5ea6\u5c06\u5185\u7f6e\u7684\u7ebf\u5f39\u6027\u6750\u6599\u6a21\u578b\u6269\u5c55\u5230 Cosserat \u5f39\u6027\u6750\u6599\uff0c\u5206\u6790\u4e86\u7eaf\u626d\u8f6c\u4f5c\u7528\u4e0b\u7684\u5706\u67f1\u5f62\u6746\uff0c\u5e76\u89c2\u5bdf Cosserat \u957f\u5ea6\u5c3a\u5ea6\u53c2\u6570\u5bf9\u54cd\u5e94\u7684\u5f71\u54cd\u3002");

    model.mesh().clearMeshes();

    model.sol("sol1").clearSolutionData();
    model.sol("sol2").clearSolutionData();
    model.sol("sol3").clearSolutionData();
    model.sol("sol4").clearSolutionData();
    model.sol("sol5").clearSolutionData();

    model.label("cosserat_torsion.mph");

    return model;
  }

  public static void main(String[] args) {
    run();
  }

}
