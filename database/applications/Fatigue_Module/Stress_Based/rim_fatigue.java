/*
 * rim_fatigue.java
 */

import com.comsol.model.*;
import com.comsol.model.util.*;

/** Model exported on Apr 30 2026, 10:03 by COMSOL 6.3.0.290. */
public class rim_fatigue {

  public static Model run() {
    Model model = ModelUtil.create("Model");

    model.modelPath("D:\\Program Files\\COMSOL\\COMSOL63\\Multiphysics\\applications\\Fatigue_Module\\Stress_Based");

    model.component().create("comp1", true);

    model.component("comp1").geom().create("geom1", 3);
    model.component("comp1").geom("geom1").geomRep("comsol");

    model.component("comp1").mesh().create("mesh1");
    model.component("comp1").mesh("mesh1").contribute("geom/detail", true);

    model.component("comp1").physics().create("solid", "SolidMechanics", "geom1");

    model.study().create("std1");
    model.study("std1").create("stat", "Stationary");
    model.study("std1").feature("stat").setSolveFor("/physics/solid", true);

    model.param().set("pInflation", "2[bar]");
    model.param().descr("pInflation", "Inflation pressure");
    model.param().set("tireLoad", "1120[kg]*g_const");
    model.param().descr("tireLoad", "Load on wheel");
    model.param().set("spokeNo", "0");
    model.param().descr("spokeNo", "Spoke selection");
    model.param().set("spokeAngle", "spokeNo*2*pi[rad]/5");
    model.param().descr("spokeAngle", "Rotation angle to selected spoke");
    model.param().set("phiLoad", "0[deg]");
    model.param().descr("phiLoad", "Peak load angle");
    model.param().set("numLpos", "4");
    model.param().descr("numLpos", "Number of load positions in first sector");
    model.param().set("angleStep", "360[deg]/(5*numLpos)");
    model.param().descr("angleStep", "Step in peak load angle [deg]");
    model.param().set("angleLast", "angleStep*(numLpos-1)");
    model.param().descr("angleLast", "Last peak load angle [deg]");

    model.component("comp1").geom("geom1").insertFile("wheel_rim_geom_sequence.mph", "geom1");
    model.component("comp1").geom("geom1").run("rmd1");

    model.component("comp1").selection().create("sel1", "Explicit");
    model.component("comp1").selection("sel1").label("TireAttachment");
    model.component("comp1").selection("sel1").geom(2);
    model.component("comp1").selection("sel1").set(12, 13, 24, 26);
    model.component("comp1").selection().create("sel2", "Explicit");
    model.component("comp1").selection("sel2").label("PressureSurface");
    model.component("comp1").selection("sel2").geom(2);
    model.component("comp1").selection("sel2").set(12, 13, 24, 25, 26, 27, 36, 37, 47, 48, 59);
    model.component("comp1").selection().create("sel3", "Explicit");
    model.component("comp1").selection("sel3").label("FixedToHub");
    model.component("comp1").selection("sel3").geom(2);
    model.component("comp1").selection("sel3").set(179, 254, 357, 447, 497);

    model.component("comp1").material().create("mat1", "Common");
    model.component("comp1").material("mat1").propertyGroup()
         .create("Enu", "Enu", "Young's modulus and Poisson's ratio");
    model.component("comp1").material("mat1").propertyGroup().create("Murnaghan", "Murnaghan", "Murnaghan");
    model.component("comp1").material("mat1").label("Aluminum");
    model.component("comp1").material("mat1").set("family", "aluminum");
    model.component("comp1").material("mat1").propertyGroup("def")
         .set("relpermeability", new String[]{"1", "0", "0", "0", "1", "0", "0", "0", "1"});
    model.component("comp1").material("mat1").propertyGroup("def").set("heatcapacity", "900[J/(kg*K)]");
    model.component("comp1").material("mat1").propertyGroup("def")
         .set("thermalconductivity", new String[]{"238[W/(m*K)]", "0", "0", "0", "238[W/(m*K)]", "0", "0", "0", "238[W/(m*K)]"});
    model.component("comp1").material("mat1").propertyGroup("def")
         .set("electricconductivity", new String[]{"3.774e7[S/m]", "0", "0", "0", "3.774e7[S/m]", "0", "0", "0", "3.774e7[S/m]"});
    model.component("comp1").material("mat1").propertyGroup("def")
         .set("relpermittivity", new String[]{"1", "0", "0", "0", "1", "0", "0", "0", "1"});
    model.component("comp1").material("mat1").propertyGroup("def")
         .set("thermalexpansioncoefficient", new String[]{"23e-6[1/K]", "0", "0", "0", "23e-6[1/K]", "0", "0", "0", "23e-6[1/K]"});
    model.component("comp1").material("mat1").propertyGroup("def").set("density", "2700[kg/m^3]");
    model.component("comp1").material("mat1").propertyGroup("Enu").set("E", "70[GPa]");
    model.component("comp1").material("mat1").propertyGroup("Enu").set("nu", "0.33");
    model.component("comp1").material("mat1").propertyGroup("Murnaghan").set("l", "-250[GPa]");
    model.component("comp1").material("mat1").propertyGroup("Murnaghan").set("m", "-330[GPa]");
    model.component("comp1").material("mat1").propertyGroup("Murnaghan").set("n", "-350[GPa]");

    model.component("comp1").physics("solid").create("fix1", "Fixed", 2);
    model.component("comp1").physics("solid").feature("fix1").selection().named("sel3");
    model.component("comp1").physics("solid").create("bndl1", "BoundaryLoad", 2);
    model.component("comp1").physics("solid").feature("bndl1").selection().named("sel2");
    model.component("comp1").physics("solid").feature("bndl1").set("forceType", "FollowerPressure");
    model.component("comp1").physics("solid").feature("bndl1").set("pressure", "pInflation");

    model.component("comp1").func().create("an1", "Analytic");
    model.component("comp1").func("an1").set("expr", "(abs(atan2(x,y)-phi)<pi/6)*cos(3*(atan2(x,y)-phi))");
    model.component("comp1").func("an1").set("args", "x, y, phi");
    model.component("comp1").func("an1").setIndex("argunit", "m", 0);
    model.component("comp1").func("an1").setIndex("argunit", "m", 1);
    model.component("comp1").func("an1").setIndex("argunit", "rad", 2);
    model.component("comp1").func("an1").set("fununit", "Pa");
    model.component("comp1").func("an1").set("funcname", "loadDistr");

    model.component("comp1").coordSystem().create("sys2", "Cylindrical");

    model.component("comp1").physics("solid").create("bndl2", "BoundaryLoad", 2);
    model.component("comp1").physics("solid").feature("bndl2").set("coordinateSystem", "sys2");
    model.component("comp1").physics("solid").feature("bndl2").selection().named("sel1");
    model.component("comp1").physics("solid").feature("bndl2")
         .set("forceReferenceArea", new String[]{"-loadAmpl*loadDistr(X,Y,phiLoad)", "0", "0.2*loadAmpl*loadDistr(X,Y,phiLoad)*(2*(Z>0)-1)"});

    model.component("comp1").cpl().create("intop1", "Integration");
    model.component("comp1").cpl("intop1").set("axisym", true);
    model.component("comp1").cpl("intop1").selection().geom("geom1", 2);
    model.component("comp1").cpl("intop1").selection().named("sel1");

    model.component("comp1").variable().create("var1");
    model.component("comp1").variable("var1").set("loadAmpl", "tireLoad/intop1(loadDistr(X,Y,0)*cos(atan2(X,Y)))");
    model.component("comp1").variable("var1").descr("loadAmpl", "Load amplitude");

    model.component("comp1").mesh("mesh1").autoMeshSize(7);
    model.component("comp1").mesh("mesh1").run();

    model.study("std1").feature("stat").set("useparam", true);
    model.study("std1").feature("stat").setIndex("pname", "pInflation", 0);
    model.study("std1").feature("stat").setIndex("plistarr", "", 0);
    model.study("std1").feature("stat").setIndex("punit", "Pa", 0);
    model.study("std1").feature("stat").setIndex("pname", "pInflation", 0);
    model.study("std1").feature("stat").setIndex("plistarr", "", 0);
    model.study("std1").feature("stat").setIndex("punit", "Pa", 0);
    model.study("std1").feature("stat").setIndex("pname", "phiLoad", 0);
    model.study("std1").feature("stat").setIndex("plistarr", "range(0,angleStep,angleLast)", 0);
    model.study("std1").feature("stat").setIndex("punit", "deg", 0);
    model.study("std1").showAutoSequences("all");

    model.sol("sol1").feature("s1").feature("i1").active(true);

    model.study("std1").createAutoSequences("all");

    model.sol("sol1").runAll();

    model.result().create("pg1", "PlotGroup3D");
    model.result("pg1").set("data", "dset1");
    model.result("pg1").setIndex("looplevel", 4, 0);
    model.result("pg1").label("Stress (solid)");
    model.result("pg1").set("frametype", "spatial");
    model.result("pg1").create("vol1", "Volume");
    model.result("pg1").feature("vol1").set("expr", new String[]{"solid.misesGp"});
    model.result("pg1").feature("vol1").set("threshold", "manual");
    model.result("pg1").feature("vol1").set("thresholdvalue", 0.2);
    model.result("pg1").feature("vol1").set("colortable", "Rainbow");
    model.result("pg1").feature("vol1").set("colortabletrans", "none");
    model.result("pg1").feature("vol1").set("colorscalemode", "linear");
    model.result("pg1").feature("vol1").set("resolution", "custom");
    model.result("pg1").feature("vol1").set("refine", 2);
    model.result("pg1").feature("vol1").set("colortable", "Prism");
    model.result("pg1").feature("vol1").create("def", "Deform");
    model.result("pg1").feature("vol1").feature("def").set("expr", new String[]{"u", "v", "w"});
    model.result("pg1").feature("vol1").feature("def").set("descr", "Displacement field");
    model.result("pg1").run();
    model.result().configuration().create("prfu1", "PreferredUnits");
    model.result().configuration("prfu1")
         .setIndex("quantityunits", new String[]{"stress", "Stress tensor", "N/m^2", "N/m^2"}, 0);
    model.result().configuration("prfu1").setIndex("quantityunits", "MPa", 0, 3);
    model.result().configuration("prfu1").set("applytosamedims", true);
    model.result().configuration("prfu1").apply();
    model.result("pg1").run();
    model.result("pg1").setIndex("looplevel", 1, 0);
    model.result("pg1").set("edges", false);
    model.result("pg1").run();
    model.result("pg1").feature("vol1").set("rangecoloractive", true);
    model.result("pg1").feature("vol1").set("rangecolormax", 90);

    model.component("comp1").view().create("view2", "geom1");
    model.component("comp1").view("view2").set("locked", true);

    model.result("pg1").run();
    model.result("pg1").set("view", "view2");
    model.result("pg1").run();
    model.result().create("pg2", "PlotGroup3D");
    model.result("pg2").set("data", "dset1");
    model.result("pg2").setIndex("looplevel", 4, 0);
    model.result("pg2").label("Boundary Loads (solid)");
    model.result("pg2").set("showlegends", true);
    model.result("pg2").set("titletype", "label");
    model.result("pg2").set("frametype", "spatial");
    model.result("pg2").set("showlegendsunit", true);
    model.result("pg2").create("surf1", "Surface");
    model.result("pg2").feature("surf1").set("expr", new String[]{"1"});
    model.result("pg2").feature("surf1").label("Gray Surfaces");
    model.result("pg2").feature("surf1").set("coloring", "uniform");
    model.result("pg2").feature("surf1").set("color", "gray");
    model.result("pg2").feature("surf1").set("inheritcolor", false);
    model.result("pg2").feature("surf1").set("inheritrange", false);
    model.result("pg2").feature("surf1").set("inherittransparency", false);
    model.result("pg2").feature("surf1").create("def", "Deform");
    model.result("pg2").feature("surf1").feature("def").set("expr", new String[]{"u", "v", "w"});
    model.result("pg2").feature("surf1").feature("def").set("descr", "Displacement field");
    model.result("pg2").feature("surf1").feature("def").set("scaleactive", true);
    model.result("pg2").feature("surf1").feature("def").set("scale", 0);
    model.result("pg2").feature("surf1").create("sel1", "Selection");
    model.result("pg2").feature("surf1").feature("sel1").selection().geom("geom1", 2);
    model.result("pg2").feature("surf1").feature("sel1").selection()
         .set(1, 2, 3, 4, 5, 6, 7, 8, 9, 10, 11, 12, 13, 14, 15, 16, 17, 18, 19, 20, 21, 22, 23, 24, 25, 26, 27, 28, 29, 30, 31, 32, 33, 34, 35, 36, 37, 38, 39, 40, 41, 42, 43, 44, 45, 46, 47, 48, 49, 50, 51, 52, 53, 54, 55, 56, 57, 58, 59, 60, 61, 62, 63, 64, 65, 66, 67, 68, 69, 70, 71, 72, 73, 74, 75, 76, 77, 78, 79, 80, 81, 82, 83, 84, 85, 86, 87, 88, 89, 90, 91, 92, 93, 94, 95, 96, 97, 98, 99, 100, 101, 102, 103, 104, 105, 106, 107, 108, 109, 110, 111, 112, 113, 114, 115, 116, 117, 118, 119, 120, 121, 122, 123, 124, 125, 126, 127, 128, 129, 130, 131, 132, 133, 134, 135, 136, 137, 138, 139, 140, 141, 142, 143, 144, 145, 146, 147, 148, 149, 150, 151, 152, 153, 154, 155, 156, 157, 158, 159, 160, 161, 162, 163, 164, 165, 166, 167, 168, 169, 170, 171, 172, 173, 174, 175, 176, 177, 178, 179, 180, 181, 182, 183, 184, 185, 186, 187, 188, 189, 190, 191, 192, 193, 194, 195, 196, 197, 198, 199, 200, 201, 202, 203, 204, 205, 206, 207, 208, 209, 210, 211, 212, 213, 214, 215, 216, 217, 218, 219, 220, 221, 222, 223, 224, 225, 226, 227, 228, 229, 230, 231, 232, 233, 234, 235, 236, 237, 238, 239, 240, 241, 242, 243, 244, 245, 246, 247, 248, 249, 250, 251, 252, 253, 254, 255, 256, 257, 258, 259, 260, 261, 262, 263, 264, 265, 266, 267, 268, 269, 270, 271, 272, 273, 274, 275, 276, 277, 278, 279, 280, 281, 282, 283, 284, 285, 286, 287, 288, 289, 290, 291, 292, 293, 294, 295, 296, 297, 298, 299, 300, 301, 302, 303, 304, 305, 306, 307, 308, 309, 310, 311, 312, 313, 314, 315, 316, 317, 318, 319, 320, 321, 322, 323, 324, 325, 326, 327, 328, 329, 330, 331, 332, 333, 334, 335, 336, 337, 338, 339, 340, 341, 342, 343, 344, 345, 346, 347, 348, 349, 350, 351, 352, 353, 354, 355, 356, 357, 358, 359, 360, 361, 362, 363, 364, 365, 366, 367, 368, 369, 370, 371, 372, 373, 374, 375, 376, 377, 378, 379, 380, 381, 382, 383, 384, 385, 386, 387, 388, 389, 390, 391, 392, 393, 394, 395, 396, 397, 398, 399, 400, 401, 402, 403, 404, 405, 406, 407, 408, 409, 410, 411, 412, 413, 414, 415, 416, 417, 418, 419, 420, 421, 422, 423, 424, 425, 426, 427, 428, 429, 430, 431, 432, 433, 434, 435, 436, 437, 438, 439, 440, 441, 442, 443, 444, 445, 446, 447, 448, 449, 450, 451, 452, 453, 454, 455, 456, 457, 458, 459, 460, 461, 462, 463, 464, 465, 466, 467, 468, 469, 470, 471, 472, 473, 474, 475, 476, 477, 478, 479, 480, 481, 482, 483, 484, 485, 486, 487, 488, 489, 490, 491, 492, 493, 494, 495, 496, 497, 498, 499, 500, 501, 502, 503, 504, 505, 506, 507, 508, 509, 510, 511, 512, 513, 514, 515, 516, 517, 518, 519, 520, 521, 522, 523, 524, 525, 526, 527, 528, 529, 530, 531, 532, 533, 534, 535, 536, 537, 538, 539, 540, 541, 542, 543, 544, 545, 546, 547, 548, 549, 550, 551, 552, 553, 554, 555, 556, 557, 558, 559, 560, 561, 562, 563, 564, 565, 566, 567, 568, 569, 570, 571, 572, 573, 574, 575, 576, 577, 578, 579, 580, 581, 582, 583, 584, 585, 586, 587, 588, 589, 590, 591, 592, 593, 594, 595, 596, 597, 598, 599, 600, 601, 602, 603, 604, 605, 606, 607, 608, 609, 610, 611, 612, 613, 614, 615, 616, 617, 618, 619, 620, 621, 622, 623, 624, 625, 626, 627, 628, 629, 630, 631, 632, 633, 634, 635, 636, 637, 638, 639, 640, 641, 642, 643, 644, 645, 646, 647, 648, 649, 650, 651, 652, 653, 654, 655, 656, 657, 658, 659, 660, 661, 662, 663, 664, 665, 666, 667, 668, 669, 670, 671, 672, 673, 674, 675, 676, 677, 678, 679, 680);
    model.result("pg2").feature("surf1").create("tran1", "Transparency");
    model.result("pg2").feature("surf1").feature("tran1").set("transparency", 0.8);
    model.result("pg2").create("arws1", "ArrowSurface");
    model.result("pg2").feature("arws1")
         .set("expr", new String[]{"solid.bndl1.fax", "solid.bndl1.fay", "solid.bndl1.faz"});
    model.result("pg2").feature("arws1").set("placement", "gausspoints");
    model.result("pg2").feature("arws1").set("arrowbase", "head");
    model.result("pg2").feature("arws1").label("Boundary Load 1");
    model.result("pg2").feature("arws1").set("inheritplot", "none");
    model.result("pg2").feature("arws1").create("col", "Color");
    model.result("pg2").feature("arws1").feature("col").set("colortable", "Rainbow");
    model.result("pg2").feature("arws1").feature("col").set("colortabletrans", "none");
    model.result("pg2").feature("arws1").feature("col").set("colorscalemode", "linear");
    model.result("pg2").feature("arws1").feature("col").set("colordata", "arrowlength");
    model.result("pg2").feature("arws1").feature("col").set("coloring", "gradient");
    model.result("pg2").feature("arws1").feature("col").set("topcolor", "red");
    model.result("pg2").feature("arws1").feature("col").set("bottomcolor", "custom");
    model.result("pg2").feature("arws1").feature("col")
         .set("custombottomcolor", new double[]{0.5882353186607361, 0.5137255191802979, 0.5176470875740051});
    model.result("pg2").feature("arws1").set("color", "red");
    model.result("pg2").feature("arws1").create("def", "Deform");
    model.result("pg2").feature("arws1").feature("def").set("expr", new String[]{"u", "v", "w"});
    model.result("pg2").feature("arws1").feature("def").set("descr", "Displacement field");
    model.result("pg2").feature("arws1").feature("def").set("scaleactive", true);
    model.result("pg2").feature("arws1").feature("def").set("scale", 0);
    model.result("pg2").feature().move("surf1", 1);
    model.result("pg2").create("arws2", "ArrowSurface");
    model.result("pg2").feature("arws2")
         .set("expr", new String[]{"solid.bndl2.fax", "solid.bndl2.fay", "solid.bndl2.faz"});
    model.result("pg2").feature("arws2").set("placement", "gausspoints");
    model.result("pg2").feature("arws2").set("arrowbase", "tail");
    model.result("pg2").feature("arws2").label("Boundary Load 2");
    model.result("pg2").feature("arws2").set("inheritplot", "arws1");
    model.result("pg2").feature("arws2").create("col", "Color");
    model.result("pg2").feature("arws2").feature("col").set("colortable", "Rainbow");
    model.result("pg2").feature("arws2").feature("col").set("colortabletrans", "none");
    model.result("pg2").feature("arws2").feature("col").set("colorscalemode", "linear");
    model.result("pg2").feature("arws2").feature("col").set("colordata", "arrowlength");
    model.result("pg2").feature("arws2").feature("col").set("coloring", "gradient");
    model.result("pg2").feature("arws2").feature("col").set("topcolor", "red");
    model.result("pg2").feature("arws2").feature("col").set("bottomcolor", "custom");
    model.result("pg2").feature("arws2").feature("col")
         .set("custombottomcolor", new double[]{0.5882353186607361, 0.5137255191802979, 0.5176470875740051});
    model.result("pg2").feature("arws2").set("color", "red");
    model.result("pg2").feature("arws2").create("def", "Deform");
    model.result("pg2").feature("arws2").feature("def").set("expr", new String[]{"u", "v", "w"});
    model.result("pg2").feature("arws2").feature("def").set("descr", "Displacement field");
    model.result("pg2").feature("arws2").feature("def").set("scaleactive", true);
    model.result("pg2").feature("arws2").feature("def").set("scale", 0);
    model.result("pg2").feature().move("surf1", 2);
    model.result("pg2").label("Boundary Loads (solid)");
    model.result("pg2").run();
    model.result("pg2").run();
    model.result("pg2").feature("arws1").set("scaleactive", true);
    model.result("pg2").feature("arws1").set("scale", "4E-2");
    model.result("pg2").run();

    model.component().create("comp2", true);

    model.component("comp2").geom().create("geom2", 3);
    model.component("comp2").geom("geom2").geomRep("comsol");

    model.component("comp2").mesh().create("mesh2");
    model.component("comp2").mesh("mesh2").contribute("geom/detail", true);

    model.component("comp2").geom("geom2").insertFile("wheel_rim_geom_sequence.mph", "geom1");
    model.component("comp2").geom("geom2").run("rmd1");
    model.component("comp2").geom("geom2").feature("rot1").active(false);
    model.component("comp2").geom("geom2").run("cmd1");
    model.component("comp2").geom("geom2").run("rot1");
    model.component("comp2").geom("geom2").create("blk1", "Block");
    model.component("comp2").geom("geom2").feature("blk1").set("size", new String[]{"6e-2", "7e-2", "6e-2"});
    model.component("comp2").geom("geom2").feature("blk1").set("pos", new String[]{"0", "6.5e-2", "6e-2"});
    model.component("comp2").geom("geom2").run("blk1");
    model.component("comp2").geom("geom2").create("int1", "Intersection");
    model.component("comp2").geom("geom2").feature("int1").selection("input").set("blk1", "imp1");
    model.component("comp2").geom("geom2").run("cmd1");
    model.component("comp2").geom("geom2").feature("rmd1").active(false);
    model.component("comp2").geom("geom2").run();

    model.component("comp2").material().create("mat2", "Common");
    model.component("comp2").material("mat2").propertyGroup()
         .create("Enu", "Enu", "Young's modulus and Poisson's ratio");
    model.component("comp2").material("mat2").propertyGroup().create("Murnaghan", "Murnaghan", "Murnaghan");
    model.component("comp2").material("mat2").label("Aluminum");
    model.component("comp2").material("mat2").set("family", "aluminum");
    model.component("comp2").material("mat2").propertyGroup("def")
         .set("relpermeability", new String[]{"1", "0", "0", "0", "1", "0", "0", "0", "1"});
    model.component("comp2").material("mat2").propertyGroup("def").set("heatcapacity", "900[J/(kg*K)]");
    model.component("comp2").material("mat2").propertyGroup("def")
         .set("thermalconductivity", new String[]{"238[W/(m*K)]", "0", "0", "0", "238[W/(m*K)]", "0", "0", "0", "238[W/(m*K)]"});
    model.component("comp2").material("mat2").propertyGroup("def")
         .set("electricconductivity", new String[]{"3.774e7[S/m]", "0", "0", "0", "3.774e7[S/m]", "0", "0", "0", "3.774e7[S/m]"});
    model.component("comp2").material("mat2").propertyGroup("def")
         .set("relpermittivity", new String[]{"1", "0", "0", "0", "1", "0", "0", "0", "1"});
    model.component("comp2").material("mat2").propertyGroup("def")
         .set("thermalexpansioncoefficient", new String[]{"23e-6[1/K]", "0", "0", "0", "23e-6[1/K]", "0", "0", "0", "23e-6[1/K]"});
    model.component("comp2").material("mat2").propertyGroup("def").set("density", "2700[kg/m^3]");
    model.component("comp2").material("mat2").propertyGroup("Enu").set("E", "70[GPa]");
    model.component("comp2").material("mat2").propertyGroup("Enu").set("nu", "0.33");
    model.component("comp2").material("mat2").propertyGroup("Murnaghan").set("l", "-250[GPa]");
    model.component("comp2").material("mat2").propertyGroup("Murnaghan").set("m", "-330[GPa]");
    model.component("comp2").material("mat2").propertyGroup("Murnaghan").set("n", "-350[GPa]");

    model.component("comp1").cpl().create("genext1", "GeneralExtrusion");
    model.component("comp1").cpl("genext1").set("opname", "from_global");
    model.component("comp1").cpl("genext1").selection().all();
    model.component("comp1").cpl("genext1").set("srcframe", "material");
    model.component("comp1").cpl("genext1")
         .set("dstmap", new String[]{"X*cos(spokeAngle)-Y*sin(spokeAngle)", "y", "z"});
    model.component("comp1").cpl("genext1").setIndex("dstmap", "Y*cos(spokeAngle)+X*sin(spokeAngle)", 1);
    model.component("comp1").cpl("genext1").setIndex("dstmap", "Z", 2);
    model.component("comp1").cpl("genext1").set("exttol", 0.5);

    model.component("comp2").physics().create("solid2", "SolidMechanics", "geom2");

    model.study("std1").feature("stat").setSolveFor("/physics/solid2", true);

    model.component("comp2").physics("solid2").create("fix1", "Fixed", 2);
    model.component("comp2").physics("solid2").feature("fix1").selection().set(5);
    model.component("comp2").physics("solid2").create("disp1", "Displacement2", 2);
    model.component("comp2").physics("solid2").feature("disp1").selection().set(1, 26, 35, 56);
    model.component("comp2").physics("solid2").feature("disp1").setIndex("Direction", "prescribed", 0);
    model.component("comp2").physics("solid2").feature("disp1")
         .setIndex("U0", "comp1.from_global(comp1.u*cos(spokeAngle)+comp1.v*sin(spokeAngle))", 0);
    model.component("comp2").physics("solid2").feature("disp1").setIndex("Direction", "prescribed", 1);
    model.component("comp2").physics("solid2").feature("disp1")
         .setIndex("U0", "comp1.from_global(comp1.v*cos(spokeAngle)-comp1.u*sin(spokeAngle))", 1);
    model.component("comp2").physics("solid2").feature("disp1").setIndex("Direction", "prescribed", 2);
    model.component("comp2").physics("solid2").feature("disp1").setIndex("U0", "comp1.from_global(comp1.w)", 2);

    model.component("comp2").mesh("mesh2").autoMeshSize(4);
    model.component("comp2").mesh("mesh2").automatic(false);
    model.component("comp2").mesh("mesh2").feature("ftet1").create("size1", "Size");
    model.component("comp2").mesh("mesh2").feature("ftet1").feature("size1").selection().geom("geom2", 2);
    model.component("comp2").mesh("mesh2").feature("ftet1").feature("size1").selection().set(14);
    model.component("comp2").mesh("mesh2").feature("ftet1").feature("size1").set("hauto", 1);
    model.component("comp2").mesh("mesh2").run();

    model.study().create("std2");
    model.study("std2").create("stat", "Stationary");
    model.study("std2").feature("stat").setSolveFor("/physics/solid", false);
    model.study("std2").feature("stat").setSolveFor("/physics/solid2", true);
    model.study("std2").feature("stat").set("usesol", true);
    model.study("std2").feature("stat").set("notsolmethod", "sol");
    model.study("std2").feature("stat").set("notstudy", "std1");
    model.study("std2").feature("stat").set("notsolnum", "all");
    model.study("std2").feature("stat").set("useparam", true);
    model.study("std2").feature("stat").set("sweeptype", "filled");
    model.study("std2").feature("stat").setIndex("pname", "pInflation", 0);
    model.study("std2").feature("stat").setIndex("plistarr", "", 0);
    model.study("std2").feature("stat").setIndex("punit", "Pa", 0);
    model.study("std2").feature("stat").setIndex("pname", "pInflation", 0);
    model.study("std2").feature("stat").setIndex("plistarr", "", 0);
    model.study("std2").feature("stat").setIndex("punit", "Pa", 0);
    model.study("std2").feature("stat").setIndex("pname", "spokeNo", 0);
    model.study("std2").feature("stat").setIndex("pname", "pInflation", 1);
    model.study("std2").feature("stat").setIndex("plistarr", "", 1);
    model.study("std2").feature("stat").setIndex("punit", "Pa", 1);
    model.study("std2").feature("stat").setIndex("pname", "pInflation", 1);
    model.study("std2").feature("stat").setIndex("plistarr", "", 1);
    model.study("std2").feature("stat").setIndex("punit", "Pa", 1);
    model.study("std2").feature("stat").setIndex("plistarr", "range(0,1,4)", 0);
    model.study("std2").feature("stat").setIndex("pname", "phiLoad", 1);
    model.study("std2").feature("stat").setIndex("plistarr", "range(0,angleStep,angleLast)", 1);
    model.study("std2").feature("stat").setIndex("punit", "deg", 1);
    model.study("std2").feature("stat").setEntry("outputmap", "solid", "none");
    model.study("std2").feature("stat").setEntry("outputmap", "solid2", "physics");
    model.study("std2").showAutoSequences("all");

    model.sol("sol2").feature("s1").feature("i1").active(true);
    model.sol("sol2").feature("s1").feature("i1").feature("mg1").set("geomuse", new String[]{"geom2"});
    model.sol("sol1").feature("s1").feature("i1").feature("mg1").set("geomuse", new String[]{"geom1"});

    model.study("std2").createAutoSequences("all");

    model.sol("sol2").runAll();

    model.result().create("pg3", "PlotGroup3D");
    model.result("pg3").set("data", "dset3");
    model.result("pg3").setIndex("looplevel", 4, 0);
    model.result("pg3").setIndex("looplevel", 5, 1);
    model.result("pg3").label("Stress (solid2)");
    model.result("pg3").set("frametype", "spatial");
    model.result("pg3").create("vol1", "Volume");
    model.result("pg3").feature("vol1").set("expr", new String[]{"solid2.misesGp"});
    model.result("pg3").feature("vol1").set("threshold", "manual");
    model.result("pg3").feature("vol1").set("thresholdvalue", 0.2);
    model.result("pg3").feature("vol1").set("colortable", "Rainbow");
    model.result("pg3").feature("vol1").set("colortabletrans", "none");
    model.result("pg3").feature("vol1").set("colorscalemode", "linear");
    model.result("pg3").feature("vol1").set("resolution", "custom");
    model.result("pg3").feature("vol1").set("refine", 2);
    model.result("pg3").feature("vol1").set("colortable", "Prism");
    model.result("pg3").feature("vol1").create("def", "Deform");
    model.result("pg3").feature("vol1").feature("def").set("expr", new String[]{"u2", "v2", "w2"});
    model.result("pg3").feature("vol1").feature("def").set("descr", "Displacement field");
    model.result("pg3").run();
    model.result("pg3").run();
    model.result("pg3").feature("vol1").set("rangecoloractive", true);
    model.result("pg3").feature("vol1").set("rangecolormax", 90);
    model.result("pg3").feature("vol1").create("mrkr1", "Marker");
    model.result("pg3").run();
    model.result("pg3").feature("vol1").feature("mrkr1").set("display", "max");
    model.result("pg3").feature("vol1").feature("mrkr1").set("precision", 3);
    model.result("pg3").run();
    model.result("pg3").setIndex("looplevel", 1, 1);
    model.result("pg3").setIndex("looplevel", 2, 0);
    model.result("pg3").create("pris1", "PrincipalSurface");
    model.result("pg3").feature("pris1").create("def1", "Deform");
    model.result("pg3").run();
    model.result("pg3").run();
    model.result("pg3").feature("pris1").set("inheritplot", "vol1");
    model.result("pg3").feature("pris1").set("inheritarrowscale", false);
    model.result("pg3").feature("pris1").set("inheritcolor", false);
    model.result("pg3").feature("pris1").set("inheritrange", false);
    model.result("pg3").run();
    model.result("pg3").label("Stress in Submodel");

    model.component("comp2").view().create("view4", "geom2");
    model.component("comp2").view("view4").set("showgrid", false);
    model.component("comp2").view("view4").set("locked", true);

    model.result("pg3").run();
    model.result("pg3").set("view", "view4");
    model.result("pg3").run();
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
    model.result().export("anim1").showFrame();
    model.result().export("anim1").set("plotgroup", "pg3");
    model.result().export("anim1").set("solnumtype", "inner");
    model.result().export("anim1")
         .set("solnum", new int[]{1, 2, 3, 4, 5, 6, 7, 8, 9, 10, 11, 12, 13, 14, 15, 16, 17, 18, 19, 20});
    model.result().export("anim1").set("framesel", "all");
    model.result().export("anim1").run();

    model.study("std1").feature("stat").setSolveFor("/physics/solid2", false);

    model.result("pg3").run();

    return model;
  }

  public static Model run2(Model model) {

    model.title("\u8f6e\u8f8b\u5b50\u6a21\u578b");

    model
         .description("\u672c\u6559\u7a0b\u4f7f\u7528\u5b50\u6a21\u578b\u6280\u672f\u7cbe\u786e\u6c42\u89e3\u8f6e\u8f8b\u4e2d\u7684\u5e94\u529b\u96c6\u4e2d\u3002\u9996\u5148\uff0c\u901a\u8fc7\u6c42\u89e3\u5168\u5c40\u6a21\u578b\u5f97\u5230\u4f4d\u79fb\uff0c\u7136\u540e\u5c06\u5176\u4f5c\u4e3a\u8fb9\u754c\u6761\u4ef6\uff0c\u5e94\u7528\u4e8e\u53d1\u751f\u5e94\u529b\u96c6\u4e2d\u7684\u533a\u57df\u5c40\u90e8\u6a21\u578b\u4e2d\u3002");

    model.label("rim_submodel.mph");

    model.result("pg3").run();

    model.component("comp2").physics().create("ftg", "Fatigue", "geom2");

    model.study("std1").feature("stat").setSolveFor("/physics/ftg", false);
    model.study("std2").feature("stat").setSolveFor("/physics/ftg", false);

    model.component("comp2").physics("ftg").create("stre1", "StressBasedModel", 2);
    model.component("comp2").physics("ftg").feature("stre1").selection()
         .set(2, 3, 4, 5, 6, 7, 8, 9, 10, 11, 12, 13, 14, 15, 16, 17, 18, 19, 20, 21, 22, 23, 24, 25, 27, 28, 29, 30, 31, 32, 33, 34, 35, 36, 37, 38, 39, 40, 41, 42, 43, 44, 45, 46, 47, 48, 49, 50, 51, 52, 53, 54, 55);
    model.component("comp2").physics("ftg").feature("stre1").set("fatigueInputPhysics", "solid2");

    model.component("comp2").material().create("mat3", "Common");
    model.component("comp2").material("mat3").selection().geom("geom2", 2);
    model.component("comp2").material("mat3").selection().all();
    model.component("comp2").material("mat3").propertyGroup()
         .create("fatigueStressFindley", "fatigueStressFindley", "Findley[Fatigue]");
    model.component("comp2").material("mat3").propertyGroup("fatigueStressFindley")
         .set("k_Findley", new String[]{"0.30"});
    model.component("comp2").material("mat3").propertyGroup("fatigueStressFindley")
         .set("f_Findley", new String[]{"84[MPa]"});

    model.study().create("std3");
    model.study("std3").create("ftge", "Fatigue");
    model.study("std3").feature("ftge").set("ftplistmethod", "manual");
    model.study("std3").feature("ftge").set("solnum", "auto");
    model.study("std3").feature("ftge").set("usesol", "off");
    model.study("std3").feature("ftge").set("outputmap", new String[]{});
    model.study("std3").feature("ftge").setSolveFor("/physics/solid", false);
    model.study("std3").feature("ftge").setSolveFor("/physics/solid2", false);
    model.study("std3").feature("ftge").setSolveFor("/physics/ftg", true);
    model.study("std3").feature("ftge").set("usesol", true);
    model.study("std3").feature("ftge").set("notsolmethod", "sol");
    model.study("std3").feature("ftge").set("notstudy", "std2");
    model.study("std3").createAutoSequences("all");

    model.sol("sol3").runAll();

    model.result().create("pg4", "PlotGroup3D");
    model.result("pg4").set("data", "dset5");
    model.result("pg4").create("surf1", "Surface");
    model.result("pg4").feature("surf1").set("expr", new String[]{"ftg.fus"});
    model.result("pg4").feature("surf1").set("colortable", "Rainbow");
    model.result("pg4").feature("surf1").set("colortabletrans", "none");
    model.result("pg4").feature("surf1").set("colorscalemode", "linear");
    model.result("pg4").feature("surf1").set("colortable", "Traffic");
    model.result("pg4").label("\u75b2\u52b3\u5229\u7528\u7387\u56e0\u5b50 (ftg)");
    model.result("pg4").feature("surf1").create("mrkr1", "Marker");
    model.result("pg4").feature("surf1").feature("mrkr1").set("precision", 3);
    model.result("pg4").feature("surf1").feature("mrkr1").set("display", "max");
    model.result("pg4").run();
    model.result("pg4").run();
    model.result("pg4").run();
    model.result("pg4").feature().duplicate("surf2", "surf1");
    model.result("pg4").run();
    model.result("pg4").run();
    model.result("pg4").feature("surf1").set("colorlegend", false);
    model.result("pg4").run();
    model.result("pg4").feature("surf1").feature().remove("mrkr1");
    model.result("pg4").run();
    model.result("pg4").run();
    model.result("pg4").feature("surf2").create("sel1", "Selection");
    model.result("pg4").feature("surf2").feature("sel1").selection().set(14);
    model.result("pg4").run();
    model.result("pg4").run();
    model.result("pg4").set("view", "view4");
    model.result().dataset().create("cpt1", "CutPoint3D");
    model.result().dataset("cpt1").set("data", "dset3");
    model.result().dataset("cpt1").set("pointx", 0.017);
    model.result().dataset("cpt1").set("pointy", 0.092);
    model.result().dataset("cpt1").set("pointz", 0.088);
    model.result().dataset("cpt1").set("snapping", "boundary");
    model.result().create("pg5", "PlotGroup1D");
    model.result("pg5").run();
    model.result("pg5").create("ptgr1", "PointGraph");
    model.result("pg5").feature("ptgr1").set("markerpos", "datapoints");
    model.result("pg5").feature("ptgr1").set("linewidth", "preference");
    model.result("pg5").feature("ptgr1").set("data", "cpt1");
    model.result("pg5").feature("ptgr1").set("expr", "solid2.sGpxx");
    model.result("pg5").feature("ptgr1").set("descr", "\u5e94\u529b\u5f20\u91cf\uff0cxx \u5206\u91cf");
    model.result("pg5").feature("ptgr1").set("linestyle", "cycle");
    model.result("pg5").feature("ptgr1").set("xdatasolnumtype", "all");
    model.result("pg5").feature("ptgr1").set("linewidth", 3);
    model.result("pg5").feature("ptgr1").set("legend", true);
    model.result("pg5").feature("ptgr1").set("legendmethod", "manual");
    model.result("pg5").feature("ptgr1").setIndex("legends", "sx", 0);
    model.result("pg5").feature().duplicate("ptgr2", "ptgr1");
    model.result("pg5").run();
    model.result("pg5").feature("ptgr2").set("expr", "solid2.sy");
    model.result("pg5").feature("ptgr2").setIndex("legends", "sy", 0);
    model.result("pg5").feature().duplicate("ptgr3", "ptgr2");
    model.result("pg5").run();
    model.result("pg5").feature("ptgr3").set("expr", "solid2.sz");
    model.result("pg5").feature("ptgr3").setIndex("legends", "sz", 0);
    model.result("pg5").feature().duplicate("ptgr4", "ptgr3");
    model.result("pg5").run();
    model.result("pg5").feature("ptgr4").set("expr", "solid2.sxy");
    model.result("pg5").feature("ptgr4").setIndex("legends", "sxy", 0);
    model.result("pg5").feature().duplicate("ptgr5", "ptgr4");
    model.result("pg5").run();
    model.result("pg5").feature("ptgr5").set("expr", "solid2.syz");
    model.result("pg5").feature("ptgr5").setIndex("legends", "syz", 0);
    model.result("pg5").feature().duplicate("ptgr6", "ptgr5");
    model.result("pg5").run();
    model.result("pg5").feature("ptgr6").set("expr", "solid2.sxz");
    model.result("pg5").feature("ptgr6").setIndex("legends", "sxz", 0);
    model.result("pg5").run();
    model.result("pg5").label("\u5e94\u529b\u5386\u53f2");
    model.result("pg5").set("titletype", "manual");
    model.result("pg5").set("title", "\u4e34\u754c\u70b9\u7684\u5e94\u529b\u5386\u53f2");
    model.result("pg5").run();
    model.result("pg4").run();

    model.title("\u8f6e\u8f8b\u75b2\u52b3\u5206\u6790");

    model
         .description("\u672c\u4f8b\u7814\u7a76\u8f6e\u8f8b\u4e2d\u8f90\u6761\u7684\u9ad8\u5468\u75b2\u52b3 (HCF)\u3002\u8f6e\u5b50\u65cb\u8f6c\u65f6\uff0c\u7ed3\u6784\u4e0a\u957f\u65f6\u95f4\u627f\u53d7\u7684\u5e94\u529b\u5c06\u53d8\u4e3a\u975e\u6bd4\u4f8b\u8f7d\u8377\u3002\u901a\u8fc7 Findley \u51c6\u5219\u8bc4\u4f30\u75b2\u52b3\u98ce\u9669\u3002");

    model.mesh().clearMeshes();

    model.sol("sol1").clearSolutionData();
    model.sol("sol2").clearSolutionData();
    model.sol("sol3").clearSolutionData();

    model.label("rim_fatigue.mph");

    return model;
  }

  public static void main(String[] args) {
    Model model = run();
    run2(model);
  }

}
